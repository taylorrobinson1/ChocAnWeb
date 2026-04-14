package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javafx.scene.control.ScrollPane;

//imports that are from our java code! 
import chocAn.MemberAccount;
import chocAn.MemberAccountController;
import chocAn.ProviderAccount;
import chocAn.ProviderAccountController;
import chocAn.ServiceRecord;
import chocAn.ServiceRecordDirectory;
import chocAn.EFTRecord;


// ========== START OF CODE FOR THE GUI ==========
public class Main extends Application {
    private MemberAccountController memberAccountController = new MemberAccountController("m");
    private ProviderAccountController providerAccountController = new ProviderAccountController("p");
    private ServiceRecordDirectory serviceRecordDirectory = new ServiceRecordDirectory("sr");

    private ProviderAccount findProviderById(String id) {
        return providerAccountController.getProviderInfo(id);
    }

    private boolean deleteProviderById(String id) {
        return providerAccountController.deleteProvider(id);
    }

    private MemberAccount findMemberById(String id) {
        return memberAccountController.getMemberInfo(id);
    }

    private boolean deleteMemberById(String id) {
        return memberAccountController.deleteMember(id);
    }
    private boolean serviceCodeExists(String code) {
    try {
        List<String> lines = Files.readAllLines(Paths.get("src/chocAn/Directory.txt"));

        for (String line : lines) {
            String[] parts = line.split(" - ");

            if (parts.length >= 1) {
                if (parts[0].trim().equals(code.trim())) {
                    return true;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
    }

    private String getServiceFee(String code) {
    try {
        List<String> lines = Files.readAllLines(Paths.get("src/chocAn/Directory.txt"));

        for (String line : lines) {
            String[] parts = line.split(" - ");
            if (parts.length >= 3 && parts[0].trim().equals(code.trim())) {
                return parts[2].trim();
            }
        }
    } catch (Exception e) {
    }

    return "";
    }


    private String getServiceName(String code) {
    try {
        List<String> lines = Files.readAllLines(Paths.get("src/chocAn/Directory.txt"));

        for (String line : lines) {
            String[] parts = line.split(" - ");
            if (parts.length >= 2 && parts[0].trim().equals(code.trim())) {
                return parts[1].trim();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return "Unknown Service";
    }




    @Override
    public void start(Stage stage) throws Exception {


        // ===== DEMO GENERATED USERS =====
        memberAccountController.addMember(new MemberAccount(
            "123456789",
            "Test Member",
            "123 Main St",
            "Tuscaloosa",
            "AL",
            "35401"
        ));
        providerAccountController.addProvider(new ProviderAccount(
            "987654321",
            "Test Provider",
            "456 Oak St",
            "Tuscaloosa",
            "AL",
            "35401",
            "Valid",
            ""
        ));

        // ===== LOGO =====
        Image logo = new Image("file:gui/ChocAn-logo.png");
        ImageView logoView = new ImageView(logo);

        logoView.setFitWidth(170); // adjust size of logo here
        logoView.setPreserveRatio(true);
        logoView.setMouseTransparent(true);

        //==== COLORS =====
        String MANAGER_LIGHT ="#FFBABB";
        String MANAGER_DARK ="#ED8496";
        String PROVIDER_LIGHT="#A4FF99";
        String PROVIDER_DARK="#5BD25A";
        String OPERATOR_LIGHT="#8FF0DB";
        String OPERATOR_DARK="#2DC2B4";
        String BG_COLOR_MAIN="#E0EAF0";
        String TXT_COLOR_MAIN="#0F4C5C";

        //===== Styling ========
         String buttonBase =
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 25px;" +
            "-fx-background-radius: 25;" +
            "-fx-padding: 16 24 16 24;" +
            "-fx-text-fill: #2f1f1f;"+
            "-fx-border-width: 5px;"+
            "-fx-background-insets: 0; " +
            "-fx-border-insets: 0; " +
            "-fx-background-color:"+BG_COLOR_MAIN+";"+
            "-fx-border-radius: 20;";

        String backButtonStyle =
            "-fx-font-family: 'Times New Roman';" +
            "-fx-font-size: 18px;" +
            "-fx-background-color: " + BG_COLOR_MAIN + ";" +
            "-fx-text-fill: " + TXT_COLOR_MAIN + ";" +
            "-fx-border-color: " + TXT_COLOR_MAIN + ";" +
            "-fx-border-width: 2px;" +
            "-fx-background-radius: 20;" +
            "-fx-border-radius: 20;" +
            "-fx-padding: 10 20 10 20;";  


        //=== MEMBER DATABASE ===


        //============ HOME PAGE ==============
        Label title = new Label("ChocAN");
        title.setStyle(
            "-fx-font-family: 'Impact';" +
            "-fx-font-size: 80px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: "+TXT_COLOR_MAIN+";"
        );

        Label subtitle = new Label("Chocoholics Anonymous Data Center");
        subtitle.setStyle(
            "-fx-font-family: 'Georgia';"+
            "-fx-font-weight: bold;" +
            "-fx-font-size: 30px;" +
            "-fx-text-fill: "+TXT_COLOR_MAIN+";"
        );

        Button managerBtn = new Button("Manager");
        Button providerBtn = new Button("Provider");
        Button operatorBtn = new Button("Operator");
        Button rapBtn=new Button("Run Accounting Procedure");


        managerBtn.setStyle(buttonBase +"-fx-background-color: "+ MANAGER_LIGHT +";"+ "-fx-text-fill: "+ MANAGER_DARK +";"+"-fx-font-weight: bold;"+  "-fx-border-color: "+MANAGER_DARK+";");
        providerBtn.setStyle(buttonBase + "-fx-background-color: "+PROVIDER_LIGHT+";" + "-fx-text-fill: "+PROVIDER_DARK+";"+"-fx-font-weight: bold;"+  "-fx-border-color: "+PROVIDER_DARK+";");
        operatorBtn.setStyle(buttonBase + "-fx-background-color: "+OPERATOR_LIGHT+";"+ "-fx-text-fill: "+OPERATOR_DARK+";"+"-fx-font-weight: bold;"+ "-fx-border-color: "+OPERATOR_DARK+";");
        rapBtn.setStyle(buttonBase + "-fx-background-color: "+BG_COLOR_MAIN+";" +"-fx-text-fill: "+TXT_COLOR_MAIN+";"+"-fx-font-weight: bold;"+ "-fx-border-color: "+TXT_COLOR_MAIN+";"+"-fx-font-size: 15px;");
       
        managerBtn.setPrefWidth(280);
        providerBtn.setPrefWidth(280);
        operatorBtn.setPrefWidth(280);
        rapBtn.setPrefWidth(280);

        Region topSpacer = new Region();
        topSpacer.setPrefHeight(70);

        Region middleSpacer = new Region();
        middleSpacer.setPrefHeight(20);

        Region bottomSpacer = new Region();
        bottomSpacer.setPrefHeight(40);

        VBox homeLayout = new VBox(20);
        homeLayout.setAlignment(Pos.TOP_CENTER);
        homeLayout.setPadding(new Insets(40, 40, 40, 40));
        homeLayout.setStyle("-fx-background-color: "+BG_COLOR_MAIN+";");

        homeLayout.getChildren().addAll(
            topSpacer,
            title,
            subtitle,
            middleSpacer,
            managerBtn,
            operatorBtn,
            providerBtn,
            rapBtn,
            bottomSpacer
        );

        StackPane root = new StackPane();
        root.getChildren().add(homeLayout);
        HBox topRight = new HBox(logoView);
        topRight.setPadding(new Insets(10, 50, 0, 0));
        topRight.setAlignment(Pos.TOP_RIGHT);
        topRight.setMouseTransparent(true);
        StackPane.setAlignment(topRight, Pos.TOP_RIGHT);
        root.getChildren().add(topRight);
        
        Scene homeScene = new Scene(root, 1200, 800);

        //============== MANAGER PAGE ==============
        VBox managerLayout = new VBox(20);
        managerLayout.setAlignment(Pos.CENTER);
        managerLayout.setStyle("-fx-background-color: "+MANAGER_LIGHT+";");

        Label managerLabel = new Label("Manager Page");
        managerLabel.setStyle(
            "-fx-font-family:'Georgia';"+
            "-fx-font-size: 35px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: "+MANAGER_DARK+";"
        );

        Button memberReportBtn = new Button("Run Member Report");
        Button providerReportBtn = new Button("Run Provider Report");
        Button managerReportBtn = new Button("Run Manager Report");
        Button eftReportBtn = new Button("Run EFT Report");

        memberReportBtn.setStyle(buttonBase +"-fx-text-fill:"+MANAGER_DARK+";"+"-fx-border-color: "+MANAGER_DARK+";");
        memberReportBtn.setPrefWidth(300);
        providerReportBtn.setStyle(buttonBase +"-fx-text-fill: "+MANAGER_DARK+";" +"-fx-border-color: "+MANAGER_DARK+";");
        providerReportBtn.setPrefWidth(300);
        managerReportBtn.setStyle(buttonBase +"-fx-text-fill: "+MANAGER_DARK+";" +"-fx-border-color: "+MANAGER_DARK+";");
        managerReportBtn.setPrefWidth(300);
        eftReportBtn.setStyle(buttonBase +"-fx-text-fill: "+MANAGER_DARK+";" +"-fx-border-color: "+MANAGER_DARK+";");
        eftReportBtn.setPrefWidth(300);



        Button backFromManager = new Button("Back");
        backFromManager.setStyle(backButtonStyle);
        managerLayout.getChildren().addAll(managerLabel,
            memberReportBtn,
            providerReportBtn, 
            managerReportBtn, 
            eftReportBtn ,
            backFromManager);
        Scene managerScene = new Scene(managerLayout,1200,800);



        // PROVIDER REPORT
        VBox providerReportLayout = new VBox(15);
        providerReportLayout.setAlignment(Pos.CENTER);
        providerReportLayout.setPadding(new Insets(30));
        providerReportLayout.setStyle("-fx-background-color:"+MANAGER_LIGHT+";");

        Label providerReportLabel = new Label("Provider Report");
        providerReportLabel.setStyle(
            "-fx-text-fill:"+MANAGER_DARK+";"+
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 35px;"+
            "-fx-font-weight: bold;"
        );
        
        TextField providerReportIdField = new TextField();
        providerReportIdField.setPromptText("Enter Provider ID");
        providerReportIdField.setMaxWidth(400);

        Label providerReportOutput = new Label("");
        providerReportOutput.setStyle(
            "-fx-text-fill:"+MANAGER_DARK+";"+
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 18px;"+
            "-fx-font-weight: bold;"+
            "-fx-padding:15;"
        );
        ScrollPane providerReportScroll = new ScrollPane(providerReportOutput);
        providerReportScroll.setFitToWidth(true);
        providerReportScroll.setPrefViewportHeight(450);
        providerReportScroll.setMaxWidth(800);




        providerReportOutput.setWrapText(true);
        providerReportOutput.setMaxWidth(700);
        providerReportOutput.setAlignment(Pos.CENTER_LEFT);

        Button generateProviderReportBtn = new Button("Generate Report");
        generateProviderReportBtn.setStyle(buttonBase+"-fx-text-fill:"+MANAGER_DARK+";"+"-fx-border-color:"+MANAGER_DARK+";");

        Button backFromProviderReport = new Button("Back");
        backFromProviderReport.setStyle(backButtonStyle);

        providerReportLayout.getChildren().addAll(
            providerReportLabel,
            providerReportIdField,
            providerReportScroll,
            generateProviderReportBtn,
            backFromProviderReport
        );

        Scene providerReportScene = new Scene(providerReportLayout, 1200,800);

    // MEMBER REPORT

        VBox memberReportLayout = new VBox(15);
        memberReportLayout.setAlignment(Pos.CENTER);
        memberReportLayout.setPadding(new Insets(30));
        memberReportLayout.setStyle("-fx-background-color:"+MANAGER_LIGHT+";");

        Label memberReportLabel = new Label ("Member Report");
        memberReportLabel.setStyle(
            "-fx-text-fill:"+MANAGER_DARK+";"+
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 35px;"+
            "-fx-font-weight: bold;"
        );

        TextField memberReportIdField = new TextField();
        memberReportIdField.setPromptText("Enter Member ID");
        memberReportIdField.setMaxWidth(400);

        Label memberReportOutput = new Label("");
        memberReportOutput.setStyle(
            "-fx-text-fill:"+MANAGER_DARK+";"+
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 18px;"+
            "-fx-font-weight: bold;"+
            "-fx-padding:15;"
        );
        ScrollPane memberReportScroll = new ScrollPane(memberReportOutput);
        memberReportScroll.setFitToWidth(true);
        memberReportScroll.setPrefViewportHeight(450);
        memberReportScroll.setMaxWidth(800);


        memberReportOutput.setWrapText(true);
        memberReportOutput.setMaxWidth(700);
        memberReportOutput.setAlignment(Pos.CENTER_LEFT);

        Button generateMemberReportBtn = new Button("Generate Report");
        generateMemberReportBtn.setStyle(buttonBase+ "-fx-text-fill:"+MANAGER_DARK+";"+"-fx-border-color: "+MANAGER_DARK+";");

        Button backFromMemberReport = new Button ("Back");
        backFromMemberReport.setStyle(backButtonStyle);

        memberReportLayout.getChildren().addAll(
            memberReportLabel,
            memberReportIdField,
            memberReportScroll,
            generateMemberReportBtn,
            backFromMemberReport
        );

        Scene memberReportScene = new Scene(memberReportLayout, 1200, 800);


    // MANAGER REPORT
        VBox managerReportLayout = new VBox(15);
        managerReportLayout.setAlignment(Pos.CENTER);
        managerReportLayout.setPadding(new Insets(30));
        managerReportLayout.setStyle("-fx-background-color:"+MANAGER_LIGHT+";");

        Label managerReportLabel = new Label("Manager Report");
        managerReportLabel.setStyle(
            "-fx-text-fill:"+MANAGER_DARK+";"+
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 35px;"+
            "-fx-font-weight: bold;"
        );

        Label managerReportOutput = new Label("");
        managerReportOutput.setStyle(
            "-fx-text-fill:"+MANAGER_DARK+";"+
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 18px;"+
            "-fx-font-weight: bold;"+
            "-fx-padding:15;"
        );
        ScrollPane managerReportScroll = new ScrollPane(managerReportOutput);
        managerReportScroll.setFitToWidth(true);
        managerReportScroll.setPrefViewportHeight(450);
        managerReportScroll.setMaxWidth(800);


        managerReportOutput.setWrapText(true);
        managerReportOutput.setMaxWidth(700);
        managerReportOutput.setAlignment(Pos.CENTER_LEFT);

        Button generateManagerReportBtn = new Button("Generate Report");
        generateManagerReportBtn.setStyle(buttonBase+"-fx-text-fill:"+MANAGER_DARK+";"+"-fx-border-color:"+MANAGER_DARK+";");

        Button backFromManagerReport = new Button("Back");
        backFromManagerReport.setStyle(backButtonStyle);

        managerReportLayout.getChildren().addAll(
            managerReportLabel,
            managerReportScroll,
            generateManagerReportBtn,
            backFromManagerReport
        );
        Scene managerReportScene = new Scene(managerReportLayout, 1200, 800);
   
    // EFT REPORT
        VBox eftReportLayout = new VBox(15);
        eftReportLayout.setAlignment(Pos.CENTER);
        eftReportLayout.setPadding(new Insets(30));
        eftReportLayout.setStyle("-fx-background-color: "+MANAGER_LIGHT+";");

        Label eftReportLabel = new Label("EFT Report");
        eftReportLabel.setStyle(
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 35px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: "+MANAGER_DARK+";"
        );

        Label eftReportOutput = new Label("");
        eftReportOutput.setStyle(
            "-fx-text-fill:"+MANAGER_DARK+";"+
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 18px;"+
            "-fx-font-weight: bold;"+
            "-fx-padding:15;"
        );

        ScrollPane eftReportScroll = new ScrollPane(eftReportOutput);
        eftReportScroll.setFitToWidth(true);
        eftReportScroll.setPrefViewportHeight(450);
        eftReportScroll.setMaxWidth(800);

        eftReportOutput.setWrapText(true);
        eftReportOutput.setMaxWidth(700);
        eftReportOutput.setAlignment(Pos.CENTER_LEFT);

        Button generateEftReportBtn = new Button ("Generate Report");
        generateEftReportBtn.setStyle(buttonBase+"-fx-text-fill:"+MANAGER_DARK+";"+"-fx-border-color:"+MANAGER_DARK+";");

        Button backFromEftReport = new Button("Back");
        backFromEftReport.setStyle(backButtonStyle);

        eftReportLayout.getChildren().addAll(
            eftReportLabel,
            eftReportScroll,
            generateEftReportBtn,
            backFromEftReport
        );

        Scene eftReportScene = new Scene(eftReportLayout, 1200,800);


        // ======= PROVIDER SCENE =========
        VBox providerLayout = new VBox(20);
        providerLayout.setAlignment(Pos.CENTER);
        providerLayout.setStyle("-fx-background-color: "+PROVIDER_LIGHT+";");

        Label providerLabel = new Label("Provider Page");
        providerLabel.setStyle(
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 35px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: "+PROVIDER_DARK+";"
        );

        Button requestDirectory =new Button("Request Directory");
        requestDirectory.setStyle(buttonBase+"-fx-text-fill:"+PROVIDER_DARK+";"+"-fx-border-color:"+PROVIDER_DARK+";");

        Button billChocan = new Button("Bill ChocAN");
        billChocan.setStyle(buttonBase+"-fx-text-fill:"+PROVIDER_DARK+";"+"-fx-border-color:"+PROVIDER_DARK+";");

        Button backFromProvider = new Button("Back");
        backFromProvider.setStyle(backButtonStyle);
        providerLayout.getChildren().addAll(providerLabel, 
            requestDirectory, 
            billChocan,
            backFromProvider);

        Scene providerScene = new Scene(providerLayout, 1200, 800);

        //REQUEST DIRECTORY
        VBox requestDirectoryLayout = new VBox(15);
        requestDirectoryLayout.setAlignment(Pos.TOP_CENTER);
        requestDirectoryLayout.setPadding(new Insets(30));
        requestDirectoryLayout.setStyle("-fx-background-color: "+PROVIDER_LIGHT+";");

        Label requestDirectoryLabel = new Label("Provider Directory");
        requestDirectoryLabel.setStyle(
            "-fx-text-fill:"+PROVIDER_DARK+";"+
            "-fx-font-family:'Georgia';"+
            "-fx-font-size: 35px;"+
            "-fx-font-weight: bold;"        
        );
    
        VBox serviceListBox = new VBox(10);
        serviceListBox.setAlignment(Pos.CENTER_LEFT);
        serviceListBox.setMaxWidth(500);
        List <String> lines = Files.readAllLines(Paths.get("src/chocAn/Directory.txt"));
        for(String line : lines){
            String[] parts = line.split(" - ");
            if(parts.length>=2){
                Label serviceLabel = new Label (parts[0]+" - "+parts[1]);
                serviceLabel.setStyle(
                    "-fx-text-fill: "+PROVIDER_DARK+";"+
                    "-fx-font-family:'Georgia';"+
                    "-fx-font-size: 20px;"
                );
                serviceListBox.getChildren().add(serviceLabel);
            }
        }

        Button backFromDirectory = new Button("Back");
        backFromDirectory.setStyle(backButtonStyle);

        Region directorySpacer = new Region();
        directorySpacer.setPrefHeight(25);

        requestDirectoryLayout.getChildren().addAll(
            requestDirectoryLabel,
            directorySpacer,
            serviceListBox,
            backFromDirectory
        );

        Scene requestDirectoryScene = new Scene(requestDirectoryLayout, 1200, 800);


        // BILL CHOCAN
        VBox billChocanLayout = new VBox(15);
        billChocanLayout.setAlignment(Pos.CENTER);
        billChocanLayout.setPadding(new Insets(30));
        billChocanLayout.setStyle("-fx-background-color:"+PROVIDER_LIGHT+";");

        Label billChocanLabel = new Label("Bill ChocAN");
        billChocanLabel.setStyle(
            "-fx-text-fill:"+PROVIDER_DARK+";"+
            "-fx-font-family:'Georgia';"+
            "-fx-font-size: 35px;"+
            "-fx-font-weight: bold;"
        );

        TextField billProviderIdField = new TextField();
        billProviderIdField.setPromptText("Provider ID");

        TextField billMemberIdField = new TextField();
        billMemberIdField.setPromptText("Member ID");

        TextField billServiceDateField = new TextField();
        billServiceDateField.setPromptText("Service Date (MM/dd/yyyy)");

        TextField billServiceCodeField = new TextField();
        billServiceCodeField.setPromptText("Service Code");

        TextField billCommentsField = new TextField();
        billCommentsField.setPromptText("Comments");

        billProviderIdField.setMaxWidth(400);
        billMemberIdField.setMaxWidth(400);
        billServiceCodeField.setMaxWidth(400);
        billServiceDateField.setMaxWidth(400);
        billCommentsField.setMaxWidth(400);


        Label billStatus = new Label("");
        billStatus.setStyle(
            "-fx-text-fill:"+PROVIDER_DARK+";"+
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 18px;"+
            "-fx-font-weight: bold;"
        );

        Label billFeeLabel = new Label("");
        billFeeLabel.setStyle(
            "-fx-text-fill:"+PROVIDER_DARK+";" +
            "-fx-font-family:'Georgia';" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        billStatus.setWrapText(true);
        billStatus.setMaxWidth(500);
        billStatus.setAlignment(Pos.CENTER);

        Button submitBillBtn = new Button("Submit Bill");
        submitBillBtn.setStyle(buttonBase+"-fx-text-fill:"+PROVIDER_DARK+";"+"-fx-border-color:"+PROVIDER_DARK+";");

        Button backFromBillChocan = new Button ("Back");
        backFromBillChocan.setStyle(backButtonStyle);

        billChocanLayout.getChildren().addAll(
            billChocanLabel,
            billProviderIdField,
            billMemberIdField,
            billServiceDateField,
            billServiceCodeField,
            billFeeLabel,
            billCommentsField,
            billStatus,
            submitBillBtn,
            backFromBillChocan
        );

        Scene billChocanScene = new Scene (billChocanLayout, 1200,800);

        // ====== OPERATOR SCENE =======
        VBox operatorLayout = new VBox(20);
        operatorLayout.setAlignment(Pos.CENTER);
        operatorLayout.setStyle("-fx-background-color: "+OPERATOR_LIGHT+";");

        Label operatorLabel = new Label("Operator Page");
        operatorLabel.setStyle(
            "-fx-font-family:'Georgia';"+
            "-fx-font-size: 35px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: "+OPERATOR_DARK+";"
        );

        // MEMBER ACTIONS
        Button addNewMember=new Button("Add New Member");
        addNewMember.setStyle(buttonBase+"-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color:"+OPERATOR_DARK+";");
        Button deleteMember=new Button("Delete Member");
        deleteMember.setStyle(buttonBase+"-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color:"+OPERATOR_DARK+";");
        Button updateMember=new Button("Update Member");
        updateMember.setStyle(buttonBase+"-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color:"+OPERATOR_DARK+";");


        // PROVIDER ACTIONS
        Button addNewProvider=new Button("Add New Provider");
        addNewProvider.setStyle(buttonBase+"-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color:"+OPERATOR_DARK+";");
        Button deleteProvider=new Button("Delete Provider");
        deleteProvider.setStyle(buttonBase+"-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color:"+OPERATOR_DARK+";");
        Button updateProvider=new Button("Update Provider");
        updateProvider.setStyle(buttonBase+"-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color:"+OPERATOR_DARK+";");


        Button backFromOperator = new Button("Back");
        backFromOperator.setStyle(backButtonStyle );
        operatorLayout.getChildren().addAll( operatorLabel,
            addNewMember,
            updateMember,
            deleteMember, 
            addNewProvider, 
            updateProvider, 
            deleteProvider, 
            backFromOperator);
        Scene operatorScene = new Scene(operatorLayout, 1200, 800);

        //=== BUTTON PAGES ====

        // ADD MEMBER
        VBox addMemberLayout = new VBox(15);
        addMemberLayout.setAlignment(Pos.CENTER);
        addMemberLayout.setPadding(new Insets(30));
        addMemberLayout.setStyle("-fx-background-color:"+OPERATOR_LIGHT+";");
        addMemberLayout.requestFocus();

        Label addMemberLabel = new Label("Add New Member");
        addMemberLabel.setStyle(
            "-fx-text-fill:"+OPERATOR_DARK+";"+
            "-fx-font-family:'Georgia';"+
            "-fx-font-size: 35px;" + 
            "-fx-font-weight: bold;"
        );

        TextField idField = new TextField();
        idField.setPromptText("Member ID (9 digits)");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField addressField = new TextField();
        addressField.setPromptText("Address");

        TextField cityField = new TextField();
        cityField.setPromptText("City");

        TextField stateField = new TextField();
        stateField.setPromptText("State");

        TextField zipField = new TextField();
        zipField.setPromptText("Zip Code");

        Label addMemberStatus = new Label("");
        addMemberStatus.setStyle(
            "-fx-text-fill:" + OPERATOR_DARK + ";" +
            "-fx-font-family:'Georgia';" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        Button submitBtn = new Button("Submit");
        submitBtn.setStyle(buttonBase+"-fx-text-fill: "+OPERATOR_DARK+";"+"-fx-border-color:"+OPERATOR_DARK+";");

        Button backToOperator = new Button("Back");
        backToOperator.setStyle(backButtonStyle);

        idField.setMaxWidth(400);
        nameField.setMaxWidth(400);
        addressField.setMaxWidth(400);
        cityField.setMaxWidth(400);
        stateField.setMaxWidth(400);
        zipField.setMaxWidth(400);


        addMemberLayout.getChildren().addAll(addMemberLabel,
            idField,
            nameField,
            addressField,
            cityField,
            stateField,
            zipField,
            addMemberStatus,
            submitBtn,
            backToOperator);
        
        Scene addMemberScene = new Scene(addMemberLayout, 1200, 800);

        // DELETE MEMBER
        VBox deleteMemberLayout = new VBox(15);
        deleteMemberLayout.setAlignment(Pos.CENTER);
        deleteMemberLayout.setPadding(new Insets(30));
        deleteMemberLayout.setStyle("-fx-background-color:" + OPERATOR_LIGHT + ";");

        Label deleteMemberLabel = new Label("Delete Member");
        deleteMemberLabel.setStyle(
            "-fx-text-fill:" + OPERATOR_DARK + ";" +
            "-fx-font-family:'Georgia';" +
            "-fx-font-size: 35px;" +
            "-fx-font-weight: bold;"
        );

        TextField deleteMemberIdField = new TextField();
        deleteMemberIdField.setPromptText("Enter Member ID"); 
        deleteMemberIdField.setMaxWidth(400);

        Label deleteMemberStatus = new Label("");
        deleteMemberStatus.setStyle(
            "-fx-text-fill:" + OPERATOR_DARK + ";" +
            "-fx-font-family:'Georgia';" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        Button searchDeleteMemberBtn = new Button("Search");
        searchDeleteMemberBtn.setStyle(buttonBase+ "-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color: "+OPERATOR_DARK+";");

        Button confirmDeleteMemberBtn = new Button("Delete");
        confirmDeleteMemberBtn.setStyle(buttonBase+ "-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color: "+OPERATOR_DARK+";");

        Button backFromDeleteMember = new Button("Back");
        backFromDeleteMember.setStyle(backButtonStyle);

        searchDeleteMemberBtn.setPrefWidth(220);
        confirmDeleteMemberBtn.setPrefWidth(220);

        HBox deleteButtonRow = new HBox(20);
        deleteButtonRow.setAlignment(Pos.CENTER);

        deleteButtonRow.getChildren().addAll(
            searchDeleteMemberBtn,
            confirmDeleteMemberBtn
        );

        deleteMemberLayout.getChildren().addAll(
            deleteMemberLabel,
            deleteMemberIdField,
            deleteMemberStatus,
            deleteButtonRow,
            backFromDeleteMember
        );
    
        Scene deleteMemberScene = new Scene(deleteMemberLayout, 1200, 800);


        // UPDATE MEMBER

        VBox updateMemberLayout = new VBox(15);
        updateMemberLayout.setAlignment(Pos.CENTER);
        updateMemberLayout.setPadding(new Insets(30));
        updateMemberLayout.setStyle("-fx-background-color: "+OPERATOR_LIGHT+";");

        Label updateMemberLabel = new Label ("Update Member");
        updateMemberLabel.setStyle(
            "-fx-text-fill:"+OPERATOR_DARK+";" +
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 35px;"+
            "-fx-font-weight: bold;"
        );

        TextField updateMemberIDField = new TextField();
        updateMemberIDField.setPromptText("Enter Member ID");
        updateMemberIDField.setMaxWidth(400);



        TextField updateNameField = new TextField();
        updateNameField.setPromptText("Name");

        TextField updateAddressField = new TextField();
        updateAddressField.setPromptText("Address");

        TextField updateCityField = new TextField();
        updateCityField.setPromptText("City");

        TextField updateStateField = new TextField();
        updateStateField.setPromptText("State");
        
        TextField updateZipField = new TextField();
        updateZipField.setPromptText("Zip Code");

        updateNameField.setMaxWidth(400);
        updateAddressField.setMaxWidth(400);
        updateCityField.setMaxWidth(400);
        updateStateField.setMaxWidth(400);
        updateZipField.setMaxWidth(400);

        Label updateMemberStatus = new Label("");
        updateMemberStatus.setStyle(
            "-fx-text-fill: "+OPERATOR_DARK+";" +
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 18px;"+
            "-fx-font-weight: bold;"
        );
        updateMemberStatus.setWrapText(true);
        updateMemberStatus.setMaxWidth(500);
        updateMemberStatus.setAlignment(Pos.CENTER);

        Button searchUpdateMemberBtn = new Button("Search");
        searchUpdateMemberBtn.setStyle(buttonBase + "-fx-text-fill: "+OPERATOR_DARK+";"+"-fx-border-color: "+OPERATOR_DARK+";");
        Button saveUpdateMemberBtn = new Button("Save Update");
        saveUpdateMemberBtn.setStyle(buttonBase + "-fx-text-fill:"+OPERATOR_DARK+";" + "-fx-border-color: "+OPERATOR_DARK+";");
        Button backFromUpdateMember = new Button("Back");
        backFromUpdateMember.setStyle(backButtonStyle);
        
        searchUpdateMemberBtn.setPrefWidth(220);
        saveUpdateMemberBtn.setPrefWidth(220);
        
        HBox updateButtonRow = new HBox(20);
        updateButtonRow.setAlignment(Pos.CENTER);
        updateButtonRow.getChildren().addAll(
            searchUpdateMemberBtn,
            saveUpdateMemberBtn
        );

        updateMemberLayout.getChildren().addAll(
            updateMemberLabel,
            updateMemberIDField,
            updateMemberStatus,
            updateNameField,
            updateAddressField,
            updateCityField,
            updateStateField,
            updateZipField,
            updateButtonRow,
            backFromUpdateMember
        );

        Scene updateMemberScene = new Scene(updateMemberLayout, 1200,800);

    // ADD PROVIDER
        VBox addProviderLayout = new VBox(15);
        addProviderLayout.setAlignment(Pos.CENTER);
        addProviderLayout.setPadding(new Insets(30));
        addProviderLayout.setStyle("-fx-background-color:"+OPERATOR_LIGHT+";");
        addProviderLayout.requestFocus();

        Label addProviderLabel = new Label("Add New Provider");
        addProviderLabel.setStyle(
            "-fx-text-fill:"+OPERATOR_DARK+";"+
            "-fx-font-family:'Georgia';"+
            "-fx-font-size: 35px;" + 
            "-fx-font-weight: bold;"
        );

        TextField providerIdField = new TextField();
        providerIdField.setPromptText("Provider ID (9 digits)");

        TextField providerNameField = new TextField();
        providerNameField.setPromptText("Name");

        TextField providerAddressField = new TextField();
        providerAddressField.setPromptText("Address");

        TextField providerCityField = new TextField();
        providerCityField.setPromptText("City");

        TextField providerStateField = new TextField();
        providerStateField.setPromptText("State");

        TextField providerZipField = new TextField();
        providerZipField.setPromptText("Zip Code");

        Label addProviderStatus = new Label("");
        addProviderStatus.setStyle(
            "-fx-text-fill:" + OPERATOR_DARK + ";" +
            "-fx-font-family:'Georgia';" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        Button submitBtnAddProvider = new Button("Submit");
        submitBtnAddProvider.setStyle(buttonBase+"-fx-text-fill: "+OPERATOR_DARK+";"+"-fx-border-color:"+OPERATOR_DARK+";");

        Button backFromAddProvider = new Button("Back");
        backFromAddProvider.setStyle(backButtonStyle);

        providerIdField.setMaxWidth(400);
        providerNameField.setMaxWidth(400);
        providerAddressField.setMaxWidth(400);
        providerCityField.setMaxWidth(400);
        providerStateField.setMaxWidth(400);
        providerZipField.setMaxWidth(400);


        addProviderLayout.getChildren().addAll(addProviderLabel,
            providerIdField,
            providerNameField,
            providerAddressField,
            providerCityField,
            providerStateField,
            providerZipField,
            addProviderStatus,
            submitBtnAddProvider,
            backFromAddProvider);
        
        Scene addProviderScene = new Scene(addProviderLayout, 1200, 800);
            
        // DELETE PROVIDER
        VBox deleteProviderLayout = new VBox(15);
        deleteProviderLayout.setAlignment(Pos.CENTER);
        deleteProviderLayout.setPadding(new Insets(30));
        deleteProviderLayout.setStyle("-fx-background-color:" + OPERATOR_LIGHT + ";");

        Label deleteProviderLabel = new Label("Delete Provider");
        deleteProviderLabel.setStyle(
            "-fx-text-fill:" + OPERATOR_DARK + ";" +
            "-fx-font-family:'Georgia';" +
            "-fx-font-size: 35px;" +
            "-fx-font-weight: bold;"
        );


        
        TextField deleteProviderIdField = new TextField();
        deleteProviderIdField.setPromptText("Enter Provider ID"); 
        deleteProviderIdField.setMaxWidth(400);

        Label deleteProviderStatus = new Label("");
        deleteProviderStatus.setStyle(
            "-fx-text-fill:" + OPERATOR_DARK + ";" +
            "-fx-font-family:'Georgia';" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"
        );

        Button searchDeleteProviderBtn = new Button("Search");
        searchDeleteProviderBtn.setStyle(buttonBase+ "-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color: "+OPERATOR_DARK+";");

        Button confirmDeleteProviderBtn = new Button("Delete");
        confirmDeleteProviderBtn.setStyle(buttonBase+ "-fx-text-fill:"+OPERATOR_DARK+";"+"-fx-border-color: "+OPERATOR_DARK+";");

        Button backFromDeleteProvider = new Button("Back");
        backFromDeleteProvider.setStyle(backButtonStyle);

        searchDeleteProviderBtn.setPrefWidth(220);
        confirmDeleteProviderBtn.setPrefWidth(220);

        HBox deleteButtonRowProvider = new HBox(20);
        deleteButtonRowProvider.setAlignment(Pos.CENTER);

        deleteButtonRowProvider.getChildren().addAll(
            searchDeleteProviderBtn,
            confirmDeleteProviderBtn
        );

        deleteProviderLayout.getChildren().addAll(
            deleteProviderLabel,
            deleteProviderIdField,
            deleteProviderStatus,
            deleteButtonRowProvider,
            backFromDeleteProvider
        );
    
        Scene deleteProviderScene = new Scene(deleteProviderLayout, 1200, 800);


        // UPDATE PROVIDER

        VBox updateProviderLayout = new VBox(15);
        updateProviderLayout.setAlignment(Pos.CENTER);
        updateProviderLayout.setPadding(new Insets(30));
        updateProviderLayout.setStyle("-fx-background-color: "+OPERATOR_LIGHT+";");

        Label updateProviderLabel = new Label ("Update Provider");
        updateProviderLabel.setStyle(
            "-fx-text-fill:"+OPERATOR_DARK+";" +
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 35px;"+
            "-fx-font-weight: bold;"
        );

        TextField updateProviderIDField = new TextField();
        updateProviderIDField.setPromptText("Enter Provider ID");
        updateProviderIDField.setMaxWidth(400);



        TextField updateProviderNameField = new TextField();
        updateProviderNameField.setPromptText("Name");

        TextField updateProviderAddressField = new TextField();
        updateProviderAddressField.setPromptText("Address");

        TextField updateProviderCityField = new TextField();
        updateProviderCityField.setPromptText("City");

        TextField updateProviderStateField = new TextField();
        updateProviderStateField.setPromptText("State");
        
        TextField updateProviderZipField = new TextField();
        updateProviderZipField.setPromptText("Zip Code");

        updateProviderNameField.setMaxWidth(400);
        updateProviderAddressField.setMaxWidth(400);
        updateProviderCityField.setMaxWidth(400);
        updateProviderStateField.setMaxWidth(400);
        updateProviderZipField.setMaxWidth(400);

        Label updateProviderStatus = new Label("");
        updateProviderStatus.setStyle(
            "-fx-text-fill: "+OPERATOR_DARK+";" +
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 18px;"+
            "-fx-font-weight: bold;"
        );
        updateProviderStatus.setWrapText(true);
        updateProviderStatus.setMaxWidth(500);
        updateProviderStatus.setAlignment(Pos.CENTER);

        Button searchUpdateProviderBtn = new Button("Search");
        searchUpdateProviderBtn.setStyle(buttonBase + "-fx-text-fill: "+OPERATOR_DARK+";"+"-fx-border-color: "+OPERATOR_DARK+";");
        Button saveUpdateProviderBtn = new Button("Save Update");
        saveUpdateProviderBtn.setStyle(buttonBase + "-fx-text-fill:"+OPERATOR_DARK+";" + "-fx-border-color: "+OPERATOR_DARK+";");
        Button backFromUpdateProvider = new Button("Back");
        backFromUpdateProvider.setStyle(backButtonStyle);
        
        searchUpdateProviderBtn.setPrefWidth(220);
        saveUpdateProviderBtn.setPrefWidth(220);
        
        HBox updateProviderButtonRow = new HBox(20);
        updateProviderButtonRow.setAlignment(Pos.CENTER);
        updateProviderButtonRow.getChildren().addAll(
            searchUpdateProviderBtn,
            saveUpdateProviderBtn
        );

        updateProviderLayout.getChildren().addAll(
            updateProviderLabel,
            updateProviderIDField,
            updateProviderStatus,
            updateProviderNameField,
            updateProviderAddressField,
            updateProviderCityField,
            updateProviderStateField,
            updateProviderZipField,
            updateProviderButtonRow,
            backFromUpdateProvider
        );

        Scene updateProviderScene = new Scene(updateProviderLayout, 1200,800);









        //rap 
        VBox rapLayout = new VBox(20);
        rapLayout.setAlignment(Pos.CENTER);
        rapLayout.setStyle("-fx-background-color: "+BG_COLOR_MAIN+";");

        Label rapLabel = new Label("Run Accounting Procedure Page");
        rapLabel.setStyle(
            "-fx-font-family: 'Georgia';"+
            "-fx-font-size: 35px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: "+TXT_COLOR_MAIN+";"
        );

        Button backFromRap = new Button("Back");
        backFromRap.setStyle(backButtonStyle);

        Button accountingProcedureBtn = new Button("Run");
        accountingProcedureBtn.setStyle(backButtonStyle);
        accountingProcedureBtn.setMaxHeight(20);
        accountingProcedureBtn.setMaxWidth(200);

        rapLayout.getChildren().addAll(rapLabel,accountingProcedureBtn ,backFromRap);

        Scene rapScene = new Scene(rapLayout,1200,800);


        // accouting procedure
        VBox accountingLayout = new VBox(15);
        accountingLayout.setAlignment(Pos.CENTER);
        accountingLayout.setPadding(new Insets(30));
        accountingLayout.setStyle("-fx-background-color:"+BG_COLOR_MAIN+";");

        Label accountingLabel = new Label ("Run Accounting Procedure");
        accountingLabel.setStyle(
            "-fx-text-fill:"+TXT_COLOR_MAIN+";" +
            "-fx-font-family:'Georgia';" +
            "-fx-font-size: 35px;" +
            "-fx-font-weight: bold;"
        );

        Label accountingOutput = new Label ("");
        accountingOutput.setStyle(
            "-fx-text-fill:"+TXT_COLOR_MAIN+";" +
            "-fx-font-family:'Georgia';" +
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;"+
            "-fx-padding:15;"
        );

        ScrollPane accountingScroll = new ScrollPane(accountingOutput);
        accountingScroll.setFitToWidth(true);
        accountingScroll.setPrefViewportHeight(450);
        accountingScroll.setMaxWidth(800);

        accountingOutput.setWrapText(true);
        accountingOutput.setMaxWidth(700);
        accountingOutput.setAlignment(Pos.CENTER_LEFT);

        Button generateAccountingBtn = new Button("Run Procedure");
        generateAccountingBtn.setStyle(buttonBase+"-fx-text-fill:"+TXT_COLOR_MAIN+";"+"-fx-border-color:"+TXT_COLOR_MAIN+";");

        Button backFromAccounting = new Button("Back");
        backFromAccounting.setStyle(backButtonStyle);

        accountingLayout.getChildren().addAll(
            accountingLabel,
            accountingScroll,
            generateAccountingBtn,
            backFromAccounting
        );

        Scene accountingScene = new Scene (accountingLayout,1200,800);


        // home page buttons
        managerBtn.setOnAction(e -> stage.setScene(managerScene));
        providerBtn.setOnAction(e -> stage.setScene(providerScene));
        operatorBtn.setOnAction(e -> stage.setScene(operatorScene));
        rapBtn.setOnAction(e -> {accountingOutput.setText(""); stage.setScene(accountingScene);});
        //back buttons to home page
        backFromManager.setOnAction(e -> stage.setScene(homeScene));
        backFromProvider.setOnAction(e -> stage.setScene(homeScene));
        backFromOperator.setOnAction(e -> stage.setScene(homeScene));
        backFromRap.setOnAction(e -> stage.setScene(homeScene));


        //add member
        addNewMember.setOnAction(e -> {
            addMemberStatus.setText("");
            stage.setScene(addMemberScene);
        });

        submitBtn.setOnAction(e -> {
            if(idField.getText().trim().length()!=9){
                addMemberStatus.setText("Member ID must be 9 digits");
                return;
            }
            if(findMemberById(idField.getText().trim())!=null){
                addMemberStatus.setText("Member ID already taken");
                return;
            }
             
            MemberAccount newMember = new MemberAccount(
                idField.getText(),
                nameField.getText(),
                addressField.getText(),
                cityField.getText(),
                stateField.getText(),
                zipField.getText());

            memberAccountController.addMember(newMember);
            addMemberStatus.setText("Member added successfully.");

            idField.clear();
            nameField.clear();
            addressField.clear();
            cityField.clear();
            stateField.clear();
            zipField.clear();
        });
        backToOperator.setOnAction(e -> stage.setScene(operatorScene));

        //delete member
        deleteMember.setOnAction(e -> {
        deleteMemberStatus.setText("");
        deleteMemberIdField.clear();
        stage.setScene(deleteMemberScene);
        });


        searchDeleteMemberBtn.setOnAction(e -> {
        String id = deleteMemberIdField.getText();
        MemberAccount found = findMemberById(id);

        if (found == null) {
            deleteMemberStatus.setText("Invalid Member ID");
        } 
        else {
            deleteMemberStatus.setText("Found Member: " + found.getName() + "\n\nClick Delete to confirm.");
        }
        });

        confirmDeleteMemberBtn.setOnAction(e -> {
        String id = deleteMemberIdField.getText();
        boolean removed = deleteMemberById(id);

        if (removed) {
            deleteMemberStatus.setText("Member deleted successfully.");
            deleteMemberIdField.clear();
        } 
        else {
            deleteMemberStatus.setText("Invalid Member ID");
        }
        });

        backFromDeleteMember.setOnAction(e -> stage.setScene(operatorScene));

        // update member

        updateMember.setOnAction(e -> {
            updateMemberStatus.setText("");
            updateMemberIDField.clear();
            updateNameField.clear();
            updateAddressField.clear();
            updateCityField.clear();
            updateStateField.clear();
            updateZipField.clear();

            stage.setScene(updateMemberScene);

        });

        searchUpdateMemberBtn.setOnAction(e ->{
        
            String id = updateMemberIDField.getText().trim();
            MemberAccount found = findMemberById(id);

            if(found == null){
                updateMemberStatus.setText("Invalid Member ID");
                updateNameField.clear();
                updateAddressField.clear();
                updateCityField.clear();
                updateStateField.clear();
                updateZipField.clear();
            }
            else{
                updateMemberStatus.setText("Member found: "+ found.getName() +" \n Edit fields and click Save Update");
                updateNameField.setText(found.getName());
                updateAddressField.setText(found.getAddress());
                updateCityField.setText(found.getCity());
                updateStateField.setText(found.getState());
                updateZipField.setText(found.getZipcode());
                
            }

        });

        saveUpdateMemberBtn.setOnAction(e ->{
            String id = updateMemberIDField.getText().trim();

            MemberAccount found = findMemberById(id);

            if(found == null){
                updateMemberStatus.setText("Invalid Member ID");
            }
            else{
                found.setName(updateNameField.getText().trim());
                found.setAddress(updateAddressField.getText().trim());
                found.setCity(updateCityField.getText().trim());
                found.setState(updateStateField.getText().trim());
                found.setZipcode(updateZipField.getText().trim());

                updateMemberStatus.setText("Member updated successfully");

            }

        });

        backFromUpdateMember.setOnAction(e -> stage.setScene(operatorScene));
        // add provider
        addNewProvider.setOnAction(e -> {
            addProviderStatus.setText("");
            providerIdField.clear();
            providerNameField.clear();
            providerAddressField.clear();
            providerCityField.clear();
            providerStateField.clear();
            providerZipField.clear();

            stage.setScene(addProviderScene);
        });

        submitBtnAddProvider.setOnAction(e -> {
            if(providerIdField.getText().trim().length()!=9){
                addProviderStatus.setText("Provider ID Must be 9 digits");
                return;
            }

            if(findProviderById(providerIdField.getText().trim())!=null){
                addProviderStatus.setText("Provider ID already exists");
                return;
            }

        ProviderAccount newProvider = new ProviderAccount(
        providerIdField.getText().trim(),
                providerNameField.getText().trim(),
                providerAddressField.getText().trim(),
                providerCityField.getText().trim(),
                providerStateField.getText().trim(),
                providerZipField.getText().trim(),
                "Valid",
                ""
        );

        providerAccountController.addProvider(newProvider);
        addProviderStatus.setText("Provider added successfully.");

            providerIdField.clear();
            providerNameField.clear();
            providerAddressField.clear();
            providerCityField.clear();
            providerStateField.clear();
            providerZipField.clear();
        });
        backFromAddProvider.setOnAction(e -> stage.setScene(operatorScene));

        //delete provider
        deleteProvider.setOnAction(e -> {
        deleteProviderStatus.setText("");
        deleteProviderIdField.clear();
        stage.setScene(deleteProviderScene);
        });
        
        searchDeleteProviderBtn.setOnAction(e -> {
            String id = deleteProviderIdField.getText().trim();
            ProviderAccount found = findProviderById(id);

            if (found == null) {
                deleteProviderStatus.setText("Invalid Provider ID");
            } 
            else {
                deleteProviderStatus.setText("Found Provider: " + found.getName() + "\n\nClick Delete to confirm.");
            }
        });

        confirmDeleteProviderBtn.setOnAction(e -> {
            String id = deleteProviderIdField.getText().trim();
            boolean removed = deleteProviderById(id);

            if (removed) {
                deleteProviderStatus.setText("Provider deleted successfully.");
                deleteProviderIdField.clear();
            } 
            else {
                deleteProviderStatus.setText("Invalid Provider ID");
            }
        });
        backFromDeleteProvider.setOnAction(e -> stage.setScene(operatorScene));

        //update provider
        updateProvider.setOnAction(e -> {
            updateProviderStatus.setText("");
            updateProviderIDField.clear();
            updateProviderNameField.clear();
            updateProviderAddressField.clear();
            updateProviderCityField.clear();
            updateProviderStateField.clear();
            updateProviderZipField.clear();

        stage.setScene(updateProviderScene);
        });

        searchUpdateProviderBtn.setOnAction(e -> {
            String id = updateProviderIDField.getText().trim();
            ProviderAccount found = findProviderById(id);

            if (found == null) {
                updateProviderStatus.setText("Invalid Provider ID");
                updateProviderNameField.clear();
                updateProviderAddressField.clear();
                updateProviderCityField.clear();
                updateProviderStateField.clear();
                updateProviderZipField.clear();
            } 
            else {
                updateProviderStatus.setText("Provider found: " + found.getName() + "\nEdit fields and click Save Update");
                updateProviderNameField.setText(found.getName());
                updateProviderAddressField.setText(found.getStreetAddress());
                updateProviderCityField.setText(found.getCity());
                updateProviderStateField.setText(found.getState());
                updateProviderZipField.setText(found.getZipcode());
            }
        });
        
        saveUpdateProviderBtn.setOnAction(e -> {
            String id = updateProviderIDField.getText().trim();
            ProviderAccount found = findProviderById(id);

            if (found == null) {
                updateProviderStatus.setText("Invalid Provider ID");
            } else {
                found.setName(updateProviderNameField.getText().trim());
                found.setStreetAddress(updateProviderAddressField.getText().trim());
                found.setCity(updateProviderCityField.getText().trim());
                found.setState(updateProviderStateField.getText().trim());
                found.setZipcode(updateProviderZipField.getText().trim());

            updateProviderStatus.setText("Provider updated successfully.");
            providerAccountController.saveProviders();
            }
        });
        backFromUpdateProvider.setOnAction(e -> stage.setScene(operatorScene));

        //request directory 
        requestDirectory.setOnAction(e -> stage.setScene(requestDirectoryScene));
        backFromDirectory.setOnAction(e -> stage.setScene(providerScene));
    

        // BILL CHOCAN
        billChocan.setOnAction(e -> {
            billFeeLabel.setText("");
            billStatus.setText("");
            billProviderIdField.clear();
            billMemberIdField.clear();
            billServiceDateField.clear();
            billServiceCodeField.clear();
            billCommentsField.clear();

            stage.setScene(billChocanScene);
        });

    submitBillBtn.setOnAction(e -> {
        String providerId = billProviderIdField.getText().trim();
        String memberId = billMemberIdField.getText().trim();
        String serviceDateText = billServiceDateField.getText().trim();
        String serviceCode = billServiceCodeField.getText().trim();
        String comments = billCommentsField.getText().trim();

        ProviderAccount provider = findProviderById(providerId);
        if (provider == null) {
            billStatus.setText("Invalid Provider ID");
            return;
        }

        if (!provider.getStatus().equals("Valid")) {
            billStatus.setText("Provider is not valid");
            return;
        }

        String memberCheck = memberAccountController.verifyMember(memberId);
        if (!memberCheck.equals("Validated")) {
            billStatus.setText(memberCheck);
            return;
        }

        if (!serviceCodeExists(serviceCode)) {
            billStatus.setText("Invalid Service Code");
            return;
        }

        String fee = getServiceFee(serviceCode);
        billFeeLabel.setText("Fee: " + fee);

        try {
        Date serviceDate = new SimpleDateFormat("MM/dd/yyyy").parse(serviceDateText);

        ServiceRecord newRecord = new ServiceRecord(
            serviceDate,
            providerId,
            memberId,
            serviceCode,
            comments
        );

        serviceRecordDirectory.addRecord(newRecord);
        billStatus.setText("Bill submitted successfully.");

        billProviderIdField.clear();
        billMemberIdField.clear();
        billServiceDateField.clear();
        billServiceCodeField.clear();
        billCommentsField.clear();

        } 
        catch (Exception ex) {
        billStatus.setText("Enter date as MM/dd/yyyy");
        }
    });

    backFromBillChocan.setOnAction(e -> stage.setScene(providerScene));


    // provider report

    providerReportBtn.setOnAction(e -> {
    providerReportIdField.clear();
    providerReportOutput.setText("");
    stage.setScene(providerReportScene);
    });

    generateProviderReportBtn.setOnAction(e -> {
    String providerId = providerReportIdField.getText().trim();
    ProviderAccount provider = findProviderById(providerId);

    if (provider == null) {
        providerReportOutput.setText("Invalid Provider ID");
        return;
    }

    StringBuilder report = new StringBuilder();

    report.append("Provider Name: ").append(provider.getName()).append("\n\n");
    report.append("Provider Number: ").append(provider.getProviderID()).append("\n\n");
    report.append("Provider Street Address: ").append(provider.getStreetAddress()).append("\n\n");
    report.append("Provider City: ").append(provider.getCity()).append("\n\n");
    report.append("Provider State: ").append(provider.getState()).append("\n\n");
    report.append("Provider ZIP Code: ").append(provider.getZipcode()).append("\n\n");

    int totalConsultations = 0;
    double totalFee = 0.0;

    for (ServiceRecord record : serviceRecordDirectory.getRecordList()) {
        if (record.getProviderNum().equals(providerId)) {
            totalConsultations++;

            String memberName = "Unknown Member";
            MemberAccount member = findMemberById(record.getMemberNum());
            if (member != null) {
                memberName = member.getName();
            }

            String feeText = getServiceFee(record.getServiceCode());
            double fee = 0.0;
            if (!feeText.isEmpty()) {
                fee = Double.parseDouble(feeText.replace("$", "").trim());
            }

            totalFee += fee;

            report.append("Date of Service: ").append(record.getServiceDate()).append("\n");
            report.append("Date and Time Received by Computer: ").append(record.getCurrentTime()).append("\n");
            report.append("Member Name: ").append(memberName).append("\n");
            report.append("Member Number: ").append(record.getMemberNum()).append("\n");
            report.append("Service Code: ").append(record.getServiceCode()).append("\n");
            report.append("Service Name: ").append(getServiceName(record.getServiceCode())).append("\n");
            report.append("Fee to be Paid: $").append(fee).append("\n\n");
        }
    }

    report.append("Total Number of Consultations: ").append(totalConsultations).append("\n\n");
    report.append("Total Fee for Week: $").append(totalFee);

    providerReportOutput.setText(report.toString());
    });

    
    backFromProviderReport.setOnAction(e -> stage.setScene(managerScene));



    // member report 

    memberReportBtn.setOnAction(e -> {
    memberReportIdField.clear();
    memberReportOutput.setText("");
    stage.setScene(memberReportScene);
    });

    generateMemberReportBtn.setOnAction(e -> {
    String memberId = memberReportIdField.getText().trim();
    MemberAccount member = findMemberById(memberId);

    if (member == null) {
        memberReportOutput.setText("Invalid Member ID");
        return;
    }

    StringBuilder report = new StringBuilder();

    report.append("Member Name: ").append(member.getName()).append("\n\n");
    report.append("Member Number: ").append(member.getMemberID()).append("\n\n");
    report.append("Member Street Address: ").append(member.getAddress()).append("\n\n");
    report.append("Member City: ").append(member.getCity()).append("\n\n");
    report.append("Member State: ").append(member.getState()).append("\n\n");
    report.append("Member ZIP Code: ").append(member.getZipcode()).append("\n\n");

    boolean foundRecord = false;

    for (ServiceRecord record : serviceRecordDirectory.getRecordList()) {
        if (record.getMemberNum().equals(memberId)) {
            foundRecord = true;

            String providerName = "Unknown Provider";
            ProviderAccount provider = findProviderById(record.getProviderNum());
            if (provider != null) {
                providerName = provider.getName();
            }

            report.append("Date of Service: ").append(record.getServiceDate()).append("\n");
            report.append("Provider Name: ").append(providerName).append("\n");
            report.append("Service Name: ").append(getServiceName(record.getServiceCode())).append("\n\n");
        }
    }

    if (!foundRecord) {
        report.append("No service records found for this member.");
    }

    memberReportOutput.setText(report.toString());
    });

    backFromMemberReport.setOnAction(e -> stage.setScene(managerScene));

    // manager report 

    managerReportBtn.setOnAction(e -> {
    managerReportOutput.setText("");
    stage.setScene(managerReportScene);
    });


    generateManagerReportBtn.setOnAction(e -> {
    StringBuilder report = new StringBuilder();

    int totalProviders = 0;
    int totalConsultations = 0;
    double overallTotalFee = 0.0;

    for (ProviderAccount provider : providerAccountController.getProviders()) {
        int providerConsultations = 0;
        double providerTotalFee = 0.0;

        for (ServiceRecord record : serviceRecordDirectory.getRecordList()) {
            if (record.getProviderNum().equals(provider.getProviderID())) {
                providerConsultations++;

                String feeText = getServiceFee(record.getServiceCode());
                double fee = 0.0;

                if (!feeText.isEmpty()) {
                    fee = Double.parseDouble(feeText.replace("$", "").trim());
                }

                providerTotalFee += fee;
            }
        }

        if (providerConsultations > 0) {
            totalProviders++;
            totalConsultations += providerConsultations;
            overallTotalFee += providerTotalFee;

            report.append("Provider Name: ").append(provider.getName()).append("\n");
            report.append("Number of Consultations: ").append(providerConsultations).append("\n");
            report.append("Total Fee: $").append(providerTotalFee).append("\n\n");
        }
    }

    report.append("Total Number of Providers Who Provided Services: ")
          .append(totalProviders).append("\n\n");

    report.append("Total Number of Consultations: ")
          .append(totalConsultations).append("\n\n");

    report.append("Overall Total Fee: $")
          .append(overallTotalFee);

    managerReportOutput.setText(report.toString());
    });

    backFromManagerReport.setOnAction(e -> stage.setScene(managerScene));

    // eft report 
    eftReportBtn.setOnAction(e -> {
    eftReportOutput.setText("");
    stage.setScene(eftReportScene);
    });

    generateEftReportBtn.setOnAction(e -> {
    StringBuilder report = new StringBuilder();

    for (ProviderAccount provider : providerAccountController.getProviders()) {
        double providerTotalFee = 0.0;

        for (ServiceRecord record : serviceRecordDirectory.getRecordList()) {
            if (record.getProviderNum().equals(provider.getProviderID())) {
                String feeText = getServiceFee(record.getServiceCode());
                double fee = 0.0;

                if (!feeText.isEmpty()) {
                    fee = Double.parseDouble(feeText.replace("$", "").trim());
                }

                providerTotalFee += fee;
            }
        }

        if (providerTotalFee > 0) {
            EFTRecord eft = new EFTRecord(
                providerTotalFee,
                provider.getName() + " - " + provider.getProviderID(),
                "ChocAn Data Center",
                new SimpleDateFormat("MM/dd/yyyy").format(new Date())
            );

            eft.createTransfer();

            report.append("Provider Name: ").append(provider.getName()).append("\n");
            report.append("Provider Number: ").append(provider.getProviderID()).append("\n");
            report.append("Amount to Transfer: $").append(String.format("%.2f", providerTotalFee)).append("\n\n");
        }
    }

    if (report.length() == 0) {
        report.append("No EFT records to generate.");
    }

    eftReportOutput.setText(report.toString());
    });

    backFromEftReport.setOnAction(e -> stage.setScene(managerScene));


    //rap 
    generateAccountingBtn.setOnAction(e -> {
    int totalProviders = 0;
    int totalConsultations = 0;
    double overallTotalFee = 0.0;

    StringBuilder report = new StringBuilder();

    for (ProviderAccount provider : providerAccountController.getProviders()) {
        int providerConsultations = 0;
        double providerTotalFee = 0.0;

        for (ServiceRecord record : serviceRecordDirectory.getRecordList()) {
            if (record.getProviderNum().equals(provider.getProviderID())) {
                providerConsultations++;

                String feeText = getServiceFee(record.getServiceCode());
                double fee = 0.0;

                if (!feeText.isEmpty()) {
                    fee = Double.parseDouble(feeText.replace("$", "").trim());
                }

                providerTotalFee += fee;
            }
        }

        if (providerConsultations > 0) {
            totalProviders++;
            totalConsultations += providerConsultations;
            overallTotalFee += providerTotalFee;

            report.append("EFT Transfer Prepared For: ").append(provider.getName()).append("\n");
            report.append("Provider Number: ").append(provider.getProviderID()).append("\n");
            report.append("Transfer Amount: $").append(String.format("%.2f", providerTotalFee)).append("\n\n");
        }
    }

    report.append("Total Providers Paid: ").append(totalProviders).append("\n\n");
    report.append("Total Consultations: ").append(totalConsultations).append("\n\n");
    report.append("Overall Total Fee: $").append(String.format("%.2f", overallTotalFee)).append("\n\n");
    report.append("Accounting procedure completed successfully.");

    accountingOutput.setText(report.toString());
    });

    backFromAccounting.setOnAction(e -> stage.setScene(homeScene));

        // stage settings
        stage.setTitle("ChocAn System");
        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
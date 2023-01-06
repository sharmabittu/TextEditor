import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    //frame for Text Editor
    JFrame frame;
    //menuBar for containing menus
    JMenuBar menuBar;
    //Menus
    JMenu file, edit;

    //Menu Items for file menu
    JMenuItem newFile, openFile, saveFile;

    //Menu Items for edit menu
    JMenuItem cut, copy, paste, selectAll, close;

    //Area for writing text
    JTextArea textArea;

    //Constructor
    TextEditor(){
        //Initialized frame
        frame = new JFrame();

        //Initialize Text Area
        textArea = new JTextArea();

        //Initialize MenuBar
        menuBar = new JMenuBar();


        //initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //initialize menu items for file menu
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        //Add Action Listener to File menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize menu items for edit menu
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close Window");

        //Add Action Listeners to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit);


        //Add menuBar to  frame
        frame.setJMenuBar(menuBar);

        //Adding text area to frame
        frame.add(textArea);

        //Set dimensions for frame
        frame.setBounds(100, 100, 800, 500);
        //Mark frame as unhidden
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //If source is cut
        if(actionEvent.getSource()==cut){
            //Perform cut
            textArea.cut();
        }
        //If source is copy
        if(actionEvent.getSource()==copy){
            //Perform copy
            textArea.copy();
        }
        //If source is paste
        if(actionEvent.getSource()==paste){
            //Perform paste
            textArea.paste();
        }
        //If source is selectAll
        if(actionEvent.getSource()==selectAll){
            //Perform selectAll
            textArea.selectAll();
        }
        //If source is close
        if(actionEvent.getSource()==close){
            //Perform close
            System.exit(0);
        }
        //If source is new
        if(actionEvent.getSource()==newFile){
            //Create a new window
            TextEditor newWindow = new TextEditor();
        }
        //If source is open
        if(actionEvent.getSource()==openFile){
            //Open a text file
            //Initialized file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //Get choose option from file chooser
            int chooseOption = fileChooser.showOpenDialog(null);
            //if choose option is equal to Approve
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Get selected file
                File file = fileChooser.getSelectedFile();
                //Get selected file path
                String filePath = file.getPath();

                try{
                    //Created Buffered reader
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                    //Create strings to store content from file
                    String intermediate = "", output = "";
                    //Read content line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output = output + intermediate+"\n";
                    }
                    //Set output to text Area
                    textArea.setText(output);
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        }
        //If source is save
        if(actionEvent.getSource()==saveFile){
            //save a file
            //Create a file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //Get chosen option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //If chosen option is Approve
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Create a file object with selected path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Create buffered writer to write content to file
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    //get content from text area to outfile
                    textArea.write(outfile);
                    outfile.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        //Initialize text editor
        TextEditor textEditor = new TextEditor();
    }
}
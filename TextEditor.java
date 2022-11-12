import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    //creating the text area
    JTextArea textArea;
    //creating the menubar
    JMenuBar jMenuBar;
    //creating constructor
    TextEditor()
    {
        //initializing the frame..
        frame=new JFrame("Text Editor");
        //initializing the textArea
        textArea=new JTextArea();
        //adding textarea into frame

        frame.add(textArea);

        jMenuBar=new JMenuBar();
        JMenu file=new JMenu("File");
        JMenu edit=new JMenu("Edit");

        JMenuItem openFile=new JMenuItem("Open File");
        JMenuItem saveFile=new JMenuItem("Save File");
        JMenuItem printFile=new JMenuItem("Print File");
        JMenuItem newFile=new JMenuItem("New File");

        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        newFile.addActionListener(this);

        file.add(openFile);
        file.add(saveFile);
        file.add(printFile);
        file.add(newFile);

        JMenuItem cut=new JMenuItem("Cut");
        JMenuItem copy=new JMenuItem("Copy");
        JMenuItem paste=new JMenuItem("Paste");
        JMenuItem close=new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(close);

        jMenuBar.add(file);
        jMenuBar.add(edit);

        frame.setJMenuBar(jMenuBar);



        frame.setVisible(true);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String call=e.getActionCommand();
        if(call=="New File")
        {
            textArea.setText("");
        }else if(call=="Cut")
        {
            textArea.cut();
        }else if(call=="Copy") {
            textArea.copy();
        }else if(call=="Paste")
        {
            textArea.paste();
        }else if(call=="Close")
        {
            frame.setVisible(false);
        }else if(call==" Save File")
        {
            JFileChooser jFileChooser=new JFileChooser("C:");
            int ans=jFileChooser.showOpenDialog(null);
            if(ans==jFileChooser.APPROVE_OPTION)
            {
                File file= new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writter=null;
                try {
                    writter = new BufferedWriter(new FileWriter(file, false));
                }catch (IOException ex){
                    throw new RuntimeException(ex);
                }

                try{
                    writter.write(textArea.getText());
                }catch (IOException ex){
                    throw new RuntimeException(ex);
                }

                try {
                    writter.flush();
                }catch (IOException ex){
                    throw new RuntimeException(ex);
                }

                try{
                    writter.close();
                }catch (IOException ex){
                    throw new RuntimeException(ex);
                }
            }

        }
        else if(call=="Open File")
        {
            JFileChooser jFileChooser1=new JFileChooser("C:");
            int ans=jFileChooser1.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION)
            {
                File file=new File(jFileChooser1.getSelectedFile().getAbsolutePath());
                try{
                    String s1="",s2="";
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while ((s1=bufferedReader.readLine())!=null)
                    {
                        s2+=s1+"\n";
                    }
                    textArea.setText(s2);
                }catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(call=="Print File")
        {
            try{
                textArea.print();
            }catch (PrinterException ex){
                throw new RuntimeException(ex);
            }
        }
    }
}

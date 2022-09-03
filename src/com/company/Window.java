package com.company;

import javax.swing.*;
import javax.swing.JScrollPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Window extends JFrame implements ActionListener{

    JFrame frame;
    JPanel footer;
    JTextPane textPane;
    StyledDocument doc;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openButton;
    JMenuItem saveButton;
    JMenuItem saveAsButton;
    JMenuItem exitButton;
    JMenu editMenu;
    JMenu addressesSubMenu;
    JMenuItem workAddressButton;
    JMenuItem homeAddressButton;
    JMenuItem studiesAddressButton;
    JMenu optionsMenu;
    JMenuItem foregroundButton;
    JMenuItem backgroundButton;
    JMenu fontSizeMenu;
    JLabel fontSizeInfo;
    //JMenuItem menuItem;



    private String openedFilePath = null;

    public Window() {

        frame = new JFrame("Simple text editor");

        menuBar = new JMenuBar();
        footer = new JPanel();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fileMenu = new JMenu("File");

        openButton = new JMenuItem("Open");
        openButton.setAccelerator(KeyStroke.getKeyStroke("control O"));
        openButton.setActionCommand("Open");
        openButton.addActionListener(this);

        saveButton = new JMenuItem("Save");
        saveButton.setAccelerator(KeyStroke.getKeyStroke("control S"));
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);


        saveAsButton = new JMenuItem("Save as");
        saveAsButton.setAccelerator(KeyStroke.getKeyStroke("control A"));
        saveAsButton.setActionCommand("Save as");
        saveAsButton.addActionListener(this);

        exitButton = new JMenuItem("Exit");
        exitButton.setAccelerator(KeyStroke.getKeyStroke("control X"));
        exitButton.setActionCommand("Exit");
        exitButton.addActionListener(this);


        fileMenu.add(openButton);
        fileMenu.add(saveButton);
        fileMenu.add(saveAsButton);
        fileMenu.add(exitButton);

        editMenu = new JMenu("Edit");

        addressesSubMenu = new JMenu("Addresses");


        workAddressButton = new JMenuItem("Work");
        workAddressButton.setAccelerator(KeyStroke.getKeyStroke("control shift P"));
        workAddressButton.setActionCommand("Work");
        workAddressButton.addActionListener(this);

        homeAddressButton = new JMenuItem("Home");
        homeAddressButton.setAccelerator(KeyStroke.getKeyStroke("control shift D"));
        homeAddressButton.setActionCommand("Home");
        homeAddressButton.addActionListener(this);

        studiesAddressButton = new JMenuItem("Studies");
        studiesAddressButton.setAccelerator(KeyStroke.getKeyStroke("control shift S"));
        studiesAddressButton.setActionCommand("Studies");
        studiesAddressButton.addActionListener(this);

        addressesSubMenu.add(workAddressButton);
        addressesSubMenu.add(homeAddressButton);
        addressesSubMenu.add(studiesAddressButton);

        editMenu.add(addressesSubMenu);


        optionsMenu = new JMenu("Options");

        foregroundButton = new JMenuItem("Foreground");
        foregroundButton.setActionCommand("Foreground");
        foregroundButton.addActionListener(this);

        backgroundButton = new JMenuItem("Background");
        backgroundButton.setActionCommand("Background");
        backgroundButton.addActionListener(this);

        fontSizeInfo = new JLabel();

        optionsMenu.add(foregroundButton);
        optionsMenu.add(backgroundButton);
        //optionsMenu.add(fontSizeMenu);


        textPane = new JTextPane();
        doc = textPane.getStyledDocument();
        scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(optionsMenu);
        footer.add(fontSizeInfo);
        scrollPane.add(footer);
        //frame.add(footer);
        frame.add(scrollPane);
        frame.setSize(500, 500);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setVisible(true);
        frame.setJMenuBar(menuBar);

    }

    public void actionPerformed(ActionEvent e) {

        String checker = e.getActionCommand();
        //File savedFile;

        if (checker.equals("Open")) {
            JFileChooser fileChooser = new JFileChooser("c:\\temp");
            int value = fileChooser.showOpenDialog(null);
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            this.openedFilePath = file.getAbsolutePath();
            System.out.println(file.getAbsolutePath());

            if (value == JFileChooser.APPROVE_OPTION) {
                try {
                    FileReader reader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    textPane.read(bufferedReader, null);
                    bufferedReader.close();
                    textPane.requestFocus();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }

        if (checker.equals("Save")) {
            try {
                FileWriter writer = null;
                File file = null;
                if (this.openedFilePath == null) {
                    JFileChooser fileChooser = new JFileChooser("c:\\temp");
                    int value = fileChooser.showSaveDialog(null);
                    if (fileChooser.getSelectedFile() != null) {
                        file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        this.openedFilePath = file.getAbsolutePath();
                    }
                    if (value == JFileChooser.APPROVE_OPTION && file != null) {
                        writer = new FileWriter(file.getAbsolutePath());
                    }
                } else
                    writer = new FileWriter(this.openedFilePath);

                if (writer != null) {
                    writer.write(textPane.getText());
                    writer.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


        if (checker.equals("Save as")) {
            JFileChooser fileChooser = new JFileChooser("c:\\temp");
            int value = fileChooser.showSaveDialog(null);
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

            if (value == JFileChooser.APPROVE_OPTION) {
                try {
                    FileWriter fileWriter = new FileWriter(file, false);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textPane.write(bufferedWriter);
                    bufferedWriter.close();
                    textPane.requestFocus();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }


        if (checker.equals("Exit")) {
            processEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

        if (checker.equals("Work")) {
            try {
                doc.insertString(textPane.getCaretPosition(), "MyWorkAddress", null);

            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }

        if (checker.equals("Home")) {
            try {
                doc.insertString(textPane.getCaretPosition(), "MyHomeAddress", null);

            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
        if (checker.equals("Studies")) {
            try {
                doc.insertString(textPane.getCaretPosition(), "MyStudiesAddress", null);

            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }


        if(checker.equals("Foreground"))

        {
            JColorChooser colorChooser = new JColorChooser();

            Color color = colorChooser.showDialog(null, "Choose a color", Color.black);

            textPane.setForeground((color));
        }

        if(checker.equals("Background"))
        {
            JColorChooser colorChooser = new JColorChooser();

            Color color = colorChooser.showDialog(null, "Choose a color", Color.black);

            textPane.setBackground((color));
        }

    }

  /*   Remove comment to run code in this class

       public static void main (String[] args){
       SwingUtilities.invokeLater(Window::new);

  }  */
}
    /*

    Font size button

    fontSizeMenu = new JMenu("Font size");
        fontSizeMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i = 8; i < 25; i += 2){
                    Font font = new Font("Arial", Font.PLAIN, i);
                    menuItem = new JMenuItem(i + " pts");
                    menuItem.setFont(font);

                    menuItem.addActionListener(new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            textPane.setFont(font);

                            fontSizeInfo.setText(Integer.toString(font.getSize()));
                        }
                    });
                    fontSizeMenu.add(menuItem);
                }
            }


            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
     */












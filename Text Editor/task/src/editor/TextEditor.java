package editor;

import editor.finder.Finder;
import editor.finder.SimpleFinder;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class TextEditor extends JFrame {

    private Finder finder = new SimpleFinder();

    private final Action useRegexAction = new AbstractAction() {
        {
            putValue(Action.SELECTED_KEY, true);
        }
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };

    private JTextField searchField;
    private JTextArea textArea;
    private JFileChooser fileChooser;

    public TextEditor() {
        initUI();
    }

    private void initUI() {
        var saveIcon = new ImageIcon("resources/img/mini/save.png");
        var openIcon = new ImageIcon("resources/img/mini/open.png");
        var searchIcon = new ImageIcon("resources/img/mini/search.png");
        var prevIcon = new ImageIcon("resources/img/mini/previous.png");
        var nextIcon = new ImageIcon("resources/img/mini/next.png");

        File homeDir = FileSystemView.getFileSystemView().getHomeDirectory();
        fileChooser = new JFileChooser(homeDir);
        fileChooser.setName("FileChooser");
        fileChooser.setVisible(false);
        add(fileChooser, BorderLayout.SOUTH);

        searchField = new JTextField();
        searchField.setName("SearchField");

        var openButton = new JButton(openIcon);
        openButton.setName("OpenButton");
        openButton.addActionListener(this::openFile);

        var saveButton = new JButton(saveIcon);
        saveButton.setName("SaveButton");
        saveButton.addActionListener(this::saveFile);

        var searchButton = new JButton(searchIcon);
        searchButton.setName("StartSearchButton");
        searchButton.addActionListener(this::startSearch);

        var prevMatchButton = new JButton(prevIcon);
        prevMatchButton.setName("PreviousMatchButton");
        prevMatchButton.addActionListener(this::previousMatch);

        var nextMatchButton = new JButton(nextIcon);
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.addActionListener(this::nextMatch);

        var useRegexCheckBox = new JCheckBox(useRegexAction);
        useRegexCheckBox.setText("Use regex");
        useRegexCheckBox.setName("UseRegExCheckbox");


        textArea = new JTextArea();
        textArea.setName("TextArea");
        textArea.setMargin(new Insets(5, 5, 5, 5));

        var scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");

        createLayout(
                openButton,
                saveButton,
                searchField,
                searchButton,
                prevMatchButton,
                nextMatchButton,
                useRegexCheckBox,
                scrollPane);

        createMenu();

        setTitle("The first stage");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
    }

    private void startSearch(ActionEvent e) {
        String whereToSearch = textArea.getText();
        String whatToSearch = searchField.getText();
        if (whereToSearch == null || whatToSearch == null) {
            return;
        }
        if (finder.startSearch(whatToSearch, whereToSearch)) {
            select(finder.start(), finder.end());
        }
    }

    private void nextMatch(ActionEvent e) {
        if (finder.next()) {
            select(finder.start(), finder.end());
        }
    }

    private void previousMatch(ActionEvent e) {
        if (finder.prev()) {
            select(finder.start(), finder.end());
        }
    }

    private void createMenu() {
        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");

        var openMenuItem = new JMenuItem("Open");
        openMenuItem.setName("MenuOpen");
        openMenuItem.addActionListener(this::openFile);

        var saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setName("MenuSave");
        saveMenuItem.addActionListener(this::saveFile);

        var exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");
        exitMenuItem.addActionListener(e -> dispose());

        var searchMenu = new JMenu("Search");
        searchMenu.setName("MenuSearch");

        var startSearchMenuItem = new JMenuItem("Start search");
        startSearchMenuItem.setName("MenuStartSearch");
        startSearchMenuItem.addActionListener(this::startSearch);

        var nextMatchMenuItem = new JMenuItem("Next match");
        nextMatchMenuItem.setName("MenuNextMatch");
        nextMatchMenuItem.addActionListener(this::nextMatch);

        var prevMatchMenuItem = new JMenuItem("Previous search");
        prevMatchMenuItem.setName("MenuPreviousMatch");
        prevMatchMenuItem.addActionListener(this::previousMatch);

        var useRegexMenuItem = new JCheckBoxMenuItem(useRegexAction);
        useRegexMenuItem.setText("Use regular expressions");
        useRegexMenuItem.setName("MenuUseRegExp");

        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        searchMenu.add(startSearchMenuItem);
        searchMenu.add(prevMatchMenuItem);
        searchMenu.add(nextMatchMenuItem);
        searchMenu.add(useRegexMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(searchMenu);

        setJMenuBar(menuBar);
    }

    private void saveFile(ActionEvent e) {
        System.out.println("Save file...");
    }

    private void openFile(ActionEvent e) {
        fileChooser.setVisible(true);
        int res = fileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            openFile(fileChooser.getSelectedFile());
        }
    }

    private void createLayout(JComponent... arg) {
        var pane = new JPanel();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        add(pane);

        gl.setAutoCreateContainerGaps(true);

        gl.setHorizontalGroup(gl.createParallelGroup()
                .addGroup(gl.createSequentialGroup()
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                        .addComponent(arg[2])
                        .addComponent(arg[3])
                        .addComponent(arg[4])
                        .addComponent(arg[5])
                        .addComponent(arg[6])
                )
                .addComponent(arg[7])
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(arg[0])
                        .addComponent(arg[1])
                        .addComponent(arg[2],
                                arg[1].getPreferredSize().height,
                                arg[1].getPreferredSize().height,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(arg[3])
                        .addComponent(arg[4])
                        .addComponent(arg[5])
                        .addComponent(arg[6])
                )
                .addComponent(arg[7])
        );
    }

    private void saveFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return;
        }
        try (Writer writer = new BufferedWriter(new FileWriter(fileName))) {
            textArea.write(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openFile(File file) {
        textArea.setText(null);
        if (file == null || !file.isFile()) {
            return;
        }
        try (Reader reader = new BufferedReader(new FileReader(file))) {
            textArea.read(reader, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void select(int start, int end) {
        textArea.setCaretPosition(end);
        textArea.select(start, end);
        textArea.grabFocus();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var app = new TextEditor();
            app.setVisible(true);
        });
    }
}

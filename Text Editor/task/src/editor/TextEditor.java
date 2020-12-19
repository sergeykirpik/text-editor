package editor;

import editor.action.*;
import editor.finder.Finder;
import editor.finder.RegexFinder;
import editor.finder.SimpleFinder;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class TextEditor extends JFrame {

    private static final Finder REGEX_FINDER = new RegexFinder();
    private static final Finder SIMPLE_FINDER = new SimpleFinder();

    private static final String ICON_PATH = "Text Editor/task/src/resources/img/";

    private Finder finder = new SimpleFinder();

    private void setFinder(Finder finder) {
        this.finder = finder;
    }

    public Finder getFinder() {
        return finder;
    }

    private final Action useRegexAction = new AbstractAction() {
        {
            putValue(Action.SELECTED_KEY, false);
            putValue(Action.NAME, "Use regular expressions");
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean useRegex = (boolean) getValue(SELECTED_KEY);
            setFinder(useRegex ? REGEX_FINDER : SIMPLE_FINDER);
        }
    };

    public JTextField searchField;
    public JTextArea textArea;

    private JFileChooser fileChooser;

    public TextEditor() {
        initUI();
    }

    private void initUI() {
        File homeDir = FileSystemView.getFileSystemView().getHomeDirectory();
        fileChooser = new JFileChooser(homeDir);
        fileChooser.setName("FileChooser");
        fileChooser.setVisible(false);
        add(fileChooser, BorderLayout.SOUTH);

        searchField = new JTextField();
        searchField.setName("SearchField");
        searchField.setBorder(BorderFactory.createCompoundBorder(
                searchField.getBorder(),
                BorderFactory.createEmptyBorder(0, 5, 0, 5)));

        var openButton = new JButton(new OpenFileAction(this));
        openButton.setName("OpenButton");

        var saveButton = new JButton(new SaveFileAction(this));
        saveButton.setName("SaveButton");

        var searchButton = new JButton(new StartSearchAction(this));
        searchButton.setName("StartSearchButton");
        searchButton.setHideActionText(true);

        var prevMatchButton = new JButton(new PreviousMatchAction(this));
        prevMatchButton.setName("PreviousMatchButton");

        var nextMatchButton = new JButton(new NextMatchAction(this));
        nextMatchButton.setName("NextMatchButton");

        var useRegexCheckBox = new JCheckBox(useRegexAction);
        useRegexCheckBox.setName("UseRegExCheckbox");
        useRegexCheckBox.setText("Use regex");

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

        setTitle("Text Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
    }

    private void createMenu() {
        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");

        var openMenuItem = new JMenuItem(new OpenFileAction(this));
        openMenuItem.setName("MenuOpen");

        var saveMenuItem = new JMenuItem(new SaveFileAction(this));
        saveMenuItem.setName("MenuSave");

        var exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setName("MenuExit");
        exitMenuItem.addActionListener(e -> dispose());

        var searchMenu = new JMenu("Search");
        searchMenu.setName("MenuSearch");

        var startSearchMenuItem = new JMenuItem(new StartSearchAction(this));
        startSearchMenuItem.setName("MenuStartSearch");

        var nextMatchMenuItem = new JMenuItem(new NextMatchAction(this));
        nextMatchMenuItem.setName("MenuNextMatch");

        var prevMatchMenuItem = new JMenuItem(new PreviousMatchAction(this));
        prevMatchMenuItem.setName("MenuPreviousMatch");

        var useRegexMenuItem = new JCheckBoxMenuItem(useRegexAction);
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
                                arg[1].getPreferredSize().height - 5,
                                arg[1].getPreferredSize().height - 5,
                                GroupLayout.PREFERRED_SIZE)
                        .addComponent(arg[3])
                        .addComponent(arg[4])
                        .addComponent(arg[5])
                        .addComponent(arg[6])
                )
                .addComponent(arg[7])
        );
    }

    public File showOpenFileDialog() {
        fileChooser.setVisible(true);
        int res = fileChooser.showOpenDialog(this);
        if (JFileChooser.APPROVE_OPTION == res) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public File showSaveFileDialog() {
        fileChooser.setVisible(true);
        int res = fileChooser.showSaveDialog(this);
        if (JFileChooser.APPROVE_OPTION == res) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public Icon getIconByName(String name) {
        return new ImageIcon(ICON_PATH + name + ".png");
    }

    public void select(int start, int end) {
        SwingUtilities.invokeLater(() -> {
            textArea.setCaretPosition(end);
            textArea.select(start, end);
            textArea.grabFocus();
        });
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var app = new TextEditor();
            app.setVisible(true);
        });
    }
}

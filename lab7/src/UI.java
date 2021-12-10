import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UI extends Thread{
    ThreadManager threadManager;

    private JFrame root = new JFrame();
    private JTable threadsTable;
    private JButton showThread = new JButton("Show");

    private JLabel threadsCountLabel = new JLabel("Threads:");
    private JTextField threadsCount = new JTextField();

    private JLabel maxFileSizeLabel = new JLabel("Max file size:");
    private JTextField maxFileSize = new JTextField();

    private JLabel filePatternLabel = new JLabel("File pattern:");
    private JTextField filePattern = new JTextField();

    Object data[][] = null;
    String columns[] = {"Name", "Status", "Priority", "Is active"};

    public UI(ThreadManager _threadManager){
        root.setSize(1000, 1000);
        root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        threadManager = _threadManager;
        threadsTable = new JTable(new DefaultTableModel(0, 0));
        threadsTable.setBounds(30, 60, 600, 600);
        root.add(threadsTable);

        /*showThread.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                threadsTable = new JTable(new DefaultTableModel(0, 0));
                threadsTable.setBounds(30, 60, 600, 600);
                root.add(threadsTable);
                threadManager = _threadManager;
            }
        });*/

        showThread.setBounds(640, 200, 100, 50);

        threadsCountLabel.setBounds(30, 30, 70, 30);
        threadsCount.setBounds(100, 30, 50, 30);

        maxFileSizeLabel.setBounds(180, 30, 100, 30);
        maxFileSize.setBounds(280, 30, 50, 30);

        filePatternLabel.setBounds(360, 30, 100, 30);
        filePattern.setBounds(460, 30, 150, 30);

        root.add(showThread);
        root.add(threadsCountLabel); root.add(threadsCount);
        root.add(maxFileSizeLabel); root.add(maxFileSize);
        root.add(filePatternLabel); root.add(filePattern);

        root.setLayout(null);
        root.setVisible(true);
    }

    @Override
    public void run() {
        while(true){
            var threads = threadManager.getThreads();
            data = new Object[threads.size()][3];
            for(int i=0; i<threads.size(); i++){
                data[i] = new Object[] {
                        threads.get(i).getName(),
                        threads.get(i).getState(),
                        threads.get(i).getPriority(),
                        threads.get(i).isAlive()
                };
            }

            threadsTable.setModel(new DefaultTableModel(data, columns));

            try{
                Thread.sleep(1000);
            }
            catch (Exception exception){
                System.out.println(exception);
            }
        }
    }

    public int getThreadsCount() {
        return Integer.parseInt(threadsCount.getText());
    }

    public int getMaxFileSize() {
        return Integer.parseInt(maxFileSize.getText());
    }

    public String getFilePattern() {
        return filePattern.getText();
    }
}

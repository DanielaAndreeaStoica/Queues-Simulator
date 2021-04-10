import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private final JLabel title;
    private JTextField queue = new JTextField();
    private JTextField tasks = new JTextField();
    private JTextField simTime = new JTextField();
    private JTextField minAT = new JTextField();
    private JTextField maxAT = new JTextField();
    private JTextField minPT = new JTextField();
    private JTextField maxPT = new JTextField();
    private JButton start = new JButton("Start");
    private JButton clear = new JButton("Clear");
    private JTextArea events = new JTextArea();
    private JScrollPane sp = new JScrollPane();

    View() {
        JFrame frame = new JFrame("Queue Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(910, 630);
        frame.setResizable(false);

        title = new JLabel();
        title.setHorizontalAlignment((int) Component.LEFT_ALIGNMENT);
        title.setText("Queue Simulator");
        Border border = new LineBorder(Color.BLACK, 2);
        title.setFont(new Font("Century", Font.BOLD, 25));
        title.setBorder(border);
        Title ttl = new Title();
        frame.add(ttl, BorderLayout.NORTH);

        JPanel space0 = new JPanel();
        space0.setPreferredSize(new Dimension(900, 20));

        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());

        JPanel a = new JPanel();
        a.setPreferredSize(new Dimension(900, 40));
        a.setLayout(new FlowLayout());
        JLabel l1 = new JLabel();
        l1.setText("Servers:");
        l1.setFont(new Font("MV BOLI", Font.BOLD, 19));
        a.add(l1);
        queue.setPreferredSize(new Dimension(100, 25));
        a.add(queue);
        JLabel l2 = new JLabel();
        l2.setText("         Tasks:");
        l2.setFont(new Font("MV BOLI", Font.BOLD, 19));
        a.add(l2);
        tasks.setPreferredSize(new Dimension(100, 25));
        a.add(tasks);
        JLabel l3 = new JLabel();
        l3.setText("      Simulation Time:");
        l3.setFont(new Font("MV BOLI", Font.BOLD, 19));
        a.add(l3);
        simTime.setPreferredSize(new Dimension(100, 25));
        a.add(simTime);

        JPanel space1 = new JPanel();
        space0.setPreferredSize(new Dimension(900, 15));

        JPanel b = new JPanel();
        b.setPreferredSize(new Dimension(900, 40));
        b.setLayout(new FlowLayout());
        JLabel l4 = new JLabel();
        l4.setText("Min Arrival Time:");
        l4.setFont(new Font("MV BOLI", Font.BOLD, 19));
        b.add(l4);
        minAT.setPreferredSize(new Dimension(100, 25));
        b.add(minAT);
        JLabel l5 = new JLabel();
        l5.setText("           Max Arrival Time:");
        l5.setFont(new Font("MV BOLI", Font.BOLD, 19));
        b.add(l5);
        maxAT.setPreferredSize(new Dimension(100, 25));
        b.add(maxAT);

        JPanel c = new JPanel();
        c.setPreferredSize(new Dimension(900, 40));
        c.setLayout(new FlowLayout());
        JLabel l6 = new JLabel();
        l6.setText("Min Processing Time:");
        l6.setFont(new Font("MV BOLI", Font.BOLD, 19));
        c.add(l6);
        minPT.setPreferredSize(new Dimension(100, 25));
        c.add(minPT);
        JLabel l7 = new JLabel();
        l7.setText("        Max Processing Time:");
        l7.setFont(new Font("MV BOLI", Font.BOLD, 19));
        c.add(l7);
        maxPT.setPreferredSize(new Dimension(100, 25));
        c.add(maxPT);

        JPanel d = new JPanel();
        d.setPreferredSize(new Dimension(900, 40));
        d.setLayout(new FlowLayout());
        start.setPreferredSize(new Dimension(350, 25));
        clear.setPreferredSize(new Dimension(350, 25));
        d.add(start);
        d.add(clear);

        sp.setBounds(100, 300, 700, 250);
        sp.setViewportView(events);
        frame.getContentPane().add(sp);


        content.add(space0);
        content.add(a);
        content.add(space1);
        content.add(b);
        content.add(space1);
        content.add(c);
        content.add(space1);
        content.add(d);
        frame.add(content);
    }

    class Title extends JPanel {

        public Title() {
            super(new BorderLayout());
            add(title);
            setBackground(Color.orange);
            setPreferredSize(new Dimension(100, 60));
        }
    }

    public void setEvents(String string) {
        events.setText(string);
    }

    public String getEvents() {
        return events.getText();
    }

    public void setTasks(String string) {
        tasks.setText(string);
    }

    public void setQueue(String string) {
        queue.setText(string);
    }

    public void setMinAT(String string) {
        minAT.setText(string);
    }

    public void setMaxAT(String string) {
        maxAT.setText(string);
    }

    public void setSimTime(String string) {
        simTime.setText(string);
    }

    public void setMinPT(String string) {
        minPT.setText(string);
    }

    public void setMaxPT(String string) {
        maxPT.setText(string);
    }

    public void setClearListener(ActionListener a) {
        clear.addActionListener(a);
    }

    public void setStartListener(ActionListener a) {
        start.addActionListener(a);
    }

    public JTextField getTasks() {
        return tasks;
    }

    public JTextField getQueue() {
        return queue;
    }

    public JTextField getSimTime() {
        return simTime;
    }

    public JTextField getMinAT() {
        return minAT;
    }

    public JTextField getMaxAT() {
        return maxAT;
    }

    public JTextField getMinPT() {
        return minPT;
    }

    public JTextField getMaxPT() {
        return maxPT;
    }
}

package Body;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];
    int blankRow;  // 空白块行坐标
    int blankCol;
    int[][] win={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    String path="D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\女生\\美女1";
    int step=0;
    JMenuItem replayItem;
    JMenuItem reloginItem;
    JMenuItem closeItem;
    JMenuItem accountItem;
    JMenuItem animal;
    JMenuItem sports;
    JMenuItem belle;

    public GameJFrame() {
        initJFrame();
        initJMenu();
        initData();
        initImage();

        this.setVisible(true);
    }

    public void initData() {
        int[] tempArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = tempArr.length - 1; i > 0; i--) {
            int index = r.nextInt(i + 1);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
            if (tempArr[i] == 15) {
                blankRow = i / 4;
                blankCol = i % 4;
            }
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();

        if(victory())        {
            //打印胜利提示（对话框形式）
            JOptionPane.showMessageDialog(this, "恭喜你，拼图完成！", "胜利", JOptionPane.INFORMATION_MESSAGE);
        }

        JLabel stepCount=new JLabel("步数："+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int number = data[i][j];
                JLabel jLabel;
                if (number == 15) { // 空白块
                    jLabel = new JLabel();
                } else { // 正常块
                    ImageIcon icon = new ImageIcon(path +"\\"+ number + ".png");
                    jLabel = new JLabel(icon);
                    int finalI = i;
                    int finalJ = j;
                    jLabel.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            handleMouseClick(finalI, finalJ);
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
                }
                jLabel.setBounds(105 * j + 95, 105 * i + 140, 105, 105);
                jLabel.setBorder(new BevelBorder(1));
                this.getContentPane().add(jLabel);
            }
        }

        JLabel background = new JLabel(new ImageIcon("D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\背景图.png"));
        background.setBounds(40, 40, 528, 580);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    private void handleMouseClick(int clickedRow, int clickedCol) {
        if (isAdjacent(clickedRow, clickedCol, blankRow, blankCol)) {
            swap(clickedRow, clickedCol, blankRow, blankCol);
            blankRow = clickedRow;
            blankCol = clickedCol;
            initImage();
        }
    }

    private boolean isAdjacent(int row1, int col1, int row2, int col2) {
        return (Math.abs(row1 - row2) == 1 && col1 == col2) || (Math.abs(col1 - col2) == 1 && row1 == row2);
    }

    public void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("拼图单机版 v.1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.addKeyListener(this);
    }


       private void initJMenu()
       {
           JMenuBar jMenuBar=new JMenuBar();

           JMenu functionJMenu =new JMenu("功能");
           JMenu aboutJMenu=new JMenu("关于");
           JMenu changeImage=new JMenu("更换图片");

           replayItem=new JMenuItem("重新游戏");
           reloginItem=new JMenuItem("重新登录");
           closeItem=new JMenuItem("关闭游戏");
           animal=new JMenuItem("动物");
           sports=new JMenuItem("动漫");
           belle=new JMenuItem("女生");
           accountItem=new JMenuItem("微信账户");

           //给菜单项绑定事件
           replayItem.addActionListener(this);
           reloginItem.addActionListener(this);
           closeItem.addActionListener(this);
           accountItem.addActionListener(this);
           animal.addActionListener(this);
           sports.addActionListener(this);
           belle.addActionListener(this);

           changeImage.add(animal);
           changeImage.add(sports);
           changeImage.add(belle);

           functionJMenu.add(replayItem);
           functionJMenu.add(reloginItem);
           functionJMenu.add(closeItem);
           functionJMenu.add(changeImage);
           aboutJMenu.add(accountItem);

           jMenuBar.add(functionJMenu);
           jMenuBar.add(aboutJMenu);

           this.setJMenuBar(jMenuBar);
       }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            int code=e.getKeyCode();
            if(code==65)
            {
                this.getContentPane().removeAll();
                JLabel all=new JLabel(new ImageIcon("D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\女生\\美女1\\all.png"));
                all.setBounds(93,140,420,420);
                this.getContentPane().add(all);

                JLabel background = new JLabel(new ImageIcon("D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\背景图.png"));
                background.setBounds(40, 40, 528, 580);
                this.getContentPane().add(background);

                this.getContentPane().repaint();
            }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory())
        {
            return;
        }

        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_DOWN: // 空白块向下移动
                if (blankRow < 3) {
                    swap(blankRow, blankCol, blankRow + 1, blankCol);
                    blankRow++;
                    initImage();
                }
                break;
            case KeyEvent.VK_UP: // 空白块向下移动
                if (blankRow > 0) {
                    swap(blankRow, blankCol, blankRow - 1, blankCol);
                    blankRow--;
                    initImage();
                }
                break;
            case KeyEvent.VK_LEFT: // 空白块向左移动
                if (blankCol > 0) {
                    swap(blankRow, blankCol, blankRow, blankCol - 1);
                    blankCol--;
                    initImage();
                }
                break;
            case KeyEvent.VK_RIGHT: // 空白块向右移动
                if (blankCol < 3) {
                    swap(blankRow, blankCol, blankRow, blankCol + 1);
                    blankCol++;
                    initImage();
                }
                break;
        }
        if(code==65)
        {
            initImage();
        }
    }

    private void swap(int x1, int y1, int x2, int y2) {
        int temp = data[x1][y1];
        data[x1][y1] = data[x2][y2];
        data[x2][y2] = temp;
        step++;
    }

    private boolean victory()
    {
        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                if(data[i][j]!=win[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj=e.getSource();
        if(obj==replayItem)
        {
            System.out.println("重新游戏");
            step=0;
            initData();
            initImage();
        }
        else if(obj==reloginItem)
        {
            System.out.println("重新登陆");
            this.dispose(); // 关闭当前游戏窗口
            new LoginJFrame(); // 打开新的登录界面
        }
        else if(obj==closeItem)
        {
            System.exit(0);
        }
        else if(obj==accountItem)
        {
            System.out.println("快来加我微信!");
            JDialog jDialog=new JDialog();
            JLabel jLabel=new JLabel(new ImageIcon("D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\我的二维码.png"));
            jLabel.setBounds(0,0,258,258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }
        else if(obj==animal)
        {
            Random r=new Random();
            int num=1;
            path="D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\动物\\动物";
            path=path+num;
            step=0;
            initData();
            initImage();
        }
        else if(obj==belle)
        {
            Random r=new Random();
            int num=r.nextInt(3)+1;
            path="D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\女生\\美女";
            path=path+num;
            step=0;
            initData();
            initImage();
        }
        else if(obj==sports)
        {
            Random r=new Random();
            int num=1;
            path="D:\\AI大数据寒假训练营\\JigsawVersion2\\图片\\动漫\\动漫";
            path=path+num;
            step=0;
            initData();
            initImage();
        }
    }
}

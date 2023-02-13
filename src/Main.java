import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*大循环*/
        Scanner userscanner = new Scanner(System.in);

        //new a game object
        GamePart gamepart = new GamePart();


        //menu_selection
        int selection = 0;

        while (true) {
            //menu output
            gamepart.menu();
            selection = userscanner.nextInt();

            //options of selection
            if (selection == 1) {
                //1.paly
                while (selection == 1) {
                    //define size of board
                    int board_height = 0;
                    int board_width = 0;
                    System.out.println("请输入棋盘行数：");
                    board_height = userscanner.nextInt();
                    System.out.println("请输入棋盘列数：");
                    board_width = userscanner.nextInt();


                    //initialize the board
                    gamepart.initialization(board_height, board_width);


                    //place pieces
                    boolean mybool = true;
                    boolean player = true;

                    do {
                        //define variable
                        int row = 0;
                        int col = 0;

                        //place & player selection
                        if (player) {
                            //tip info
                            System.out.print("玩家1输入要放置的行坐标：");
                            row = userscanner.nextInt();
                            System.out.print("玩家1输入要放置的列坐标：");
                            col = userscanner.nextInt();
                            gamepart.p1place(row, col);
                            player = !player;
                        } else {
                            //tip info
                            System.out.print("玩家2输入要放置的行坐标：");
                            row = userscanner.nextInt();
                            System.out.print("玩家2输入要放置的列坐标：");
                            col = userscanner.nextInt();
                            gamepart.p2place(row, col);
                            player = !player;
                        }
                    } while (mybool);//获胜条件(modify the mybool)


                    //一轮游戏结束
                    System.out.println("请问客官还想再玩一局嘛？玩(键入数字1),不玩(键入数字0)");
                    selection = userscanner.nextInt();
                }
            } else if (selection == 2) {
                //2.settings
                Settings settings = new Settings();
                settings.print();

                char tmp;
                System.out.println("退出按q/Q");
                tmp = userscanner.next().charAt(0);
            } else if (selection == 3) {
                //3.members
                Members members = new Members();
                members.print();

                char tmp;
                System.out.println("退出按q/Q");
                tmp = userscanner.next().charAt(0);
            } else if (selection == 4) {
                //4.quit
                break;
            }
        }

        
    }
}

class GamePart {
    //数据变量定义
    char init_board[][];
    int height = 0;
    int width = 0;


    //menu info
    public void menu() {
        System.out.println("------------------KIKI五子棋------------------");
        System.out.print("1.开始游戏\t");
        System.out.println("2.设置");
        System.out.print("3.人员名单\t");
        System.out.println("4.退出游戏");
        System.out.println("请姥爷选择(输入选项前的数字)：");
    }


    //棋盘初始化
    public void initialization(int board_height, int board_width) {
        this.height = board_height;
        this.width = board_width;

        //调用赋值函数
        this.init_board = board_size_assign(board_height, board_width);

        //刷新棋盘
        for (int i = 0; i <= board_height; ++i) {
            for (int j = 0; j <= board_width; ++j) {
                if (j == board_width) {
                    System.out.println(this.init_board[i][j]);///////////////////////
                }else {
                    System.out.print(this.init_board[i][j]);
                }
            }
        }
        System.out.println();
    }


    //棋盘赋值初始化
    public char[][] board_size_assign(int board_height, int board_width) {
        char init_board[][] = new char[board_height + 5][board_width + 5];
        for (int i = 0; i <= board_height + 1; ++i) {
            for (int j = 0; j <= board_width + 1; ++j) {
                if (i == 0) {
                    String tmp = Integer.toString(j);
                    char tmpc = tmp.charAt(0);
                    init_board[i][j] = tmpc;
                    if (j == 0) {
                        String _tmp = Integer.toString(i);
                        char _tmpc = _tmp.charAt(0);
                        init_board[i][j] = _tmpc;
                    }
                }else if (j == 0) {
                    String tmp = Integer.toString(i);
                    char tmpc = tmp.charAt(0);
                    init_board[i][j] = tmpc;
                }else {
                    init_board[i][j] = '+';
                }
            }
        }
        return init_board;
    }


    //p1棋子放置
    public void p1place(int row, int col) {
        this.init_board[row][col] = '$';
        loadNewboard();
    }


    //p2棋子放置
    public void p2place(int row, int col) {
        this.init_board[row][col] = '@';
        loadNewboard();
    }


    //加载刷新后的棋盘
    public void loadNewboard() {
        for (int i = 0; i <= height; ++i) {
            for (int j = 0; j <= width; ++j) {
                if (j == width) {
                    System.out.println(this.init_board[i][j]);////////////////////
                }else {
                    System.out.print(this.init_board[i][j]);//////////////
                }
            }
        }
        System.out.println();
    }

}

class Members {
    public void print() {
        System.out.println(">>>>>>>>>>>>>>>>>制作人员<<<<<<<<<<<<<<<<<<");
        System.out.println("@KIKI\t");
        System.out.println("------------------------------------------");
    }
}

class Settings {
    public void print() {
        System.out.println(">>>>>>>>>>>>>>>>>>>设置<<<<<<<<<<<<<<<<<<<<");
        System.out.println("1.无敌\t\t" + "no");
        System.out.println("2.自杀\t\t" + "no");
        System.out.println("3.一招秒杀\t" + "no");
        System.out.println("------------------------------------------");
    }
}

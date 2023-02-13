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
                    System.out.println("请输入棋盘行数：(>=5)");
                    board_height = userscanner.nextInt();
                    System.out.println("请输入棋盘列数：(>=5)");
                    board_width = userscanner.nextInt();


                    //initialize the board
                    gamepart.initialization(board_height, board_width);


                    //place pieces
                    boolean winbool;
                    boolean player = true;
                    int winplayer = 0;

                    do {
                        //define variable
                        int row = 0;
                        int col = 0;
                        winbool = true;

                        //place & player selection
                        if (player) {
                            //tip info
                            System.out.print("玩家1输入要放置的行坐标：");
                            row = userscanner.nextInt();
                            System.out.print("玩家1输入要放置的列坐标：");
                            col = userscanner.nextInt();

                            winbool = gamepart.p1place(row, col);

                            if (winbool) {
                                winplayer = 1;
                            }
                            player = !player;
                        }else {
                            //tip info
                            System.out.print("玩家2输入要放置的行坐标：");
                            row = userscanner.nextInt();
                            System.out.print("玩家2输入要放置的列坐标：");
                            col = userscanner.nextInt();

                            winbool = gamepart.p2place(row, col);

                            if (winbool) {
                                winplayer = 2;
                            }
                            player = !player;
                        }
                    } while (!winbool);//获胜条件(modify the mybool)

                    //输赢判断
                    if (winplayer == 1) {
                        System.out.println("玩家1获胜！！！");
                    }else if (winplayer == 2) {
                        System.out.println("玩家2获胜！！！");
                    }

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
        for (int i = 1; i <= board_height + 1; ++i) {
            for (int j = 1; j <= board_width + 1; ++j) {
                if (j == board_width + 1) {
                    System.out.println(this.init_board[i][j]);///////////////////////
                } else {
                    System.out.print(this.init_board[i][j]);
                }
            }
        }
        System.out.println();
    }


    //棋盘赋值初始化
    public char[][] board_size_assign(int board_height, int board_width) {
        char init_board[][] = new char[board_height + 5][board_width + 5];
        for (int i = 0; i <= board_height + 3; ++i) {
            for (int j = 0; j <= board_width + 3; ++j) {
                if (i == 1) {
                    String tmp = Integer.toString(j - 1);
                    char tmpc = tmp.charAt(0);
                    init_board[i][j] = tmpc;
                    if (j == 1 || j == 0) {
                        //String _tmp = Integer.toString(i);
                        //char _tmpc = _tmp.charAt(0);
                        init_board[i][j] = '0';
                    }
                } else if (j == 1) {
                    if (i == 0) {
                        init_board[i][j] = '0';
                    } else {
                        String tmp = Integer.toString(i - 1);
                        char tmpc = tmp.charAt(0);
                        init_board[i][j] = tmpc;
                    }
                } else if ((i >= 2 && i <= board_height + 1) && (j >= 2 && j <= board_width + 1)) {
                    init_board[i][j] = '+';
                } else {
                    init_board[i][j] = '0';
                }
            }
        }
        return init_board;
    }


    //p1棋子放置
    public boolean p1place(int row, int col) {
        this.init_board[++row][++col] = '$';
        loadNewboard();
        int tmprow = row;
        int tmpcol = col;

        //正对角线遍历
        int p1counter = 0;
        int twist = 0;
        for (int i = 0; i < 10; ++i) {
            if (p1counter == 4) {
                return true;  //p2胜利
            } else if (twist == 2 && p1counter != 4) {  //第二个条件加不加都行
                break;  //正对角线无win条件，退出
            } else if (twist == 0) {
                tmprow--;
                tmpcol--;
                if (isBoundary(this.init_board[tmprow][tmpcol]) && this.init_board[tmprow][tmpcol] != '$') {
                    tmprow = row + 1;
                    tmpcol = col + 1;
                    twist++;
                } else if (this.init_board[tmprow][tmpcol] == '$') {
                    p1counter++;
                }
            } else if (twist == 1) {
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '$') {
                    twist++;
                } else if (this.init_board[tmprow][tmpcol] == '$') {
                    p1counter++;
                    tmprow++;
                    tmpcol++;
                }
            }
        }

        tmprow = row;
        tmpcol = col;

        //反对角线遍历
        p1counter = 0;
        twist = 0;
        for (int i = 0; i < 10; ++i) {
            if (p1counter == 4) {  //除本位外有4个连续
                return true;  //p2胜利
            } else if (twist == 2 && p1counter != 4) {  //两边都被中断且未达到4个连续(第二个条件加不加都行)
                break;  //反对角线无win条件，退出
            } else if (twist == 0) {  //一次中断也没有，右上
                //向右上
                tmprow--;
                tmpcol++;
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '$') {
                    //边界或连续中断
                    tmprow = row + 1;
                    tmpcol = col - 1;
                    twist++;  //中断值加一
                } else if (this.init_board[tmprow][tmpcol] == '$') {
                    p1counter++;  //连续值加一
                }
            } else if (twist == 1) {  //被中断一次
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '$') {
                    twist++;  //中断值加一，已被中断两次，下次循环退出
                } else if (this.init_board[tmprow][tmpcol] == '$') {
                    p1counter++;
                    tmprow++;
                    tmpcol--;
                }
            }
        }

        tmprow = row;
        tmpcol = col;

        //同行遍历
        p1counter = 0;
        twist = 0;
        for (int i = 0; i < 10; ++i) {
            if (p1counter == 4) {  //除本位外有4个连续
                return true;  //p2胜利
            } else if (twist == 2 && p1counter != 4) {  //两边都被中断且未达到4个连续(第二个条件加不加都行)
                break;  //同行无win条件，退出
            } else if (twist == 0) {  //一次中断也没有，向左
                //向左
                //tmprow;
                tmpcol--;
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '$') {
                    //边界或连续中断
                    //tmprow = ++row;
                    tmpcol = col + 1;
                    twist++;  //中断值加一
                } else if (this.init_board[tmprow][tmpcol] == '$') {
                    p1counter++;  //连续值加一
                }
            } else if (twist == 1) {  //被中断一次
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '$') {
                    twist++;  //中断值加一，已被中断两次，下次循环退出
                } else if (this.init_board[tmprow][tmpcol] == '$') {
                    p1counter++;
                    //tmprow++;
                    tmpcol++;
                }
            }
        }

        tmprow = row;
        tmpcol = col;

        //同列遍历
        p1counter = 0;
        twist = 0;
        for (int i = 0; i < 10; ++i) {
            if (p1counter == 4) {  //除本位外有4个连续
                return true;  //p2胜利
            } else if (twist == 2 && p1counter != 4) {  //两边都被中断且未达到4个连续(第二个条件加不加都行)
                break;  //同列无win条件，退出
            } else if (twist == 0) {  //一次中断也没有，向左
                //向左
                tmprow--;
                //tmpcol++;
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '$') {
                    //边界或连续中断
                    tmprow = row + 1;
                    //tmpcol = --col;
                    twist++;  //中断值加一
                } else if (this.init_board[tmprow][tmpcol] == '$') {
                    p1counter++;  //连续值加一
                }
            } else if (twist == 1) {  //被中断一次
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '$') {
                    twist++;  //中断值加一，已被中断两次，下次循环退出
                } else if (this.init_board[tmprow][tmpcol] == '$') {
                    p1counter++;
                    tmprow++;
                    //tmpcol--;
                }
            }
        }
        return false;
    }


    //p2棋子放置
    public boolean p2place(int row, int col) {
        this.init_board[++row][++col] = '@';
        loadNewboard();
        int tmprow = row;
        int tmpcol = col;

        //正对角线遍历
        int p2counter = 0;
        int twist = 0;
        for (int i = 0; i < 10; ++i) {
            if (p2counter == 4) {
                return true;  //p2胜利
            } else if (twist == 2 && p2counter != 4) {  //第二个条件加不加都行
                break;  //正对角线无win条件，退出
            } else if (twist == 0) {
                tmprow--;
                tmpcol--;
                if (isBoundary(this.init_board[tmprow][tmpcol]) && this.init_board[tmprow][tmpcol] != '@') {
                    tmprow = row + 1;
                    tmpcol = col + 1;
                    twist++;
                } else if (this.init_board[tmprow][tmpcol] == '@') {
                    p2counter++;
                }
            } else if (twist == 1) {
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '@') {
                    twist++;
                } else if (this.init_board[tmprow][tmpcol] == '@') {
                    p2counter++;
                    tmprow++;
                    tmpcol++;
                }
            }
        }

        tmprow = row;
        tmpcol = col;

        //反对角线遍历
        p2counter = 0;
        twist = 0;
        for (int i = 0; i < 10; ++i) {
            if (p2counter == 4) {  //除本位外有4个连续
                return true;  //p2胜利
            } else if (twist == 2 && p2counter != 4) {  //两边都被中断且未达到4个连续(第二个条件加不加都行)
                break;  //反对角线无win条件，退出
            } else if (twist == 0) {  //一次中断也没有，右上
                //向右上
                tmprow--;
                tmpcol++;
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '@') {
                    //边界或连续中断
                    tmprow = row + 1;
                    tmpcol = col - 1;
                    twist++;  //中断值加一
                } else if (this.init_board[tmprow][tmpcol] == '@') {
                    p2counter++;  //连续值加一
                }
            } else if (twist == 1) {  //被中断一次
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '@') {
                    twist++;  //中断值加一，已被中断两次，下次循环退出
                } else if (this.init_board[tmprow][tmpcol] == '@') {
                    p2counter++;
                    tmprow++;
                    tmpcol--;
                }
            }
        }

        tmprow = row;
        tmpcol = col;

        //同行遍历
        p2counter = 0;
        twist = 0;
        for (int i = 0; i < 10; ++i) {
            if (p2counter == 4) {  //除本位外有4个连续
                return true;  //p2胜利
            } else if (twist == 2 && p2counter != 4) {  //两边都被中断且未达到4个连续(第二个条件加不加都行)
                break;  //同行无win条件，退出
            } else if (twist == 0) {  //一次中断也没有，向左
                //向左
                //tmprow;
                tmpcol--;
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '@') {
                    //边界或连续中断
                    //tmprow = ++row;
                    tmpcol = col + 1;
                    twist++;  //中断值加一
                } else if (this.init_board[tmprow][tmpcol] == '@') {
                    p2counter++;  //连续值加一
                }
            } else if (twist == 1) {  //被中断一次
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '@') {
                    twist++;  //中断值加一，已被中断两次，下次循环退出
                } else if (this.init_board[tmprow][tmpcol] == '@') {
                    p2counter++;
                    //tmprow++;
                    tmpcol++;
                }
            }
        }

        tmprow = row;
        tmpcol = col;

        //同列遍历
        p2counter = 0;
        twist = 0;
        for (int i = 0; i < 10; ++i) {
            if (p2counter == 4) {  //除本位外有4个连续
                return true;  //p2胜利
            } else if (twist == 2 && p2counter != 4) {  //两边都被中断且未达到4个连续(第二个条件加不加都行)
                break;  //同列无win条件，退出
            } else if (twist == 0) {  //一次中断也没有，向左
                //向左
                tmprow--;
                //tmpcol++;
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '@') {
                    //边界或连续中断
                    tmprow = row + 1;
                    //tmpcol = --col;
                    twist++;  //中断值加一
                } else if (this.init_board[tmprow][tmpcol] == '@') {
                    p2counter++;  //连续值加一
                }
            } else if (twist == 1) {  //被中断一次
                if (isBoundary(this.init_board[tmprow][tmpcol]) || this.init_board[tmprow][tmpcol] != '@') {
                    twist++;  //中断值加一，已被中断两次，下次循环退出
                } else if (this.init_board[tmprow][tmpcol] == '@') {
                    p2counter++;
                    tmprow++;
                    //tmpcol--;
                }
            }
        }
        return false;
    }


    //加载刷新后的棋盘
    public void loadNewboard() {
        for (int i = 1; i <= height + 1; ++i) {
            for (int j = 1; j <= width + 1; ++j) {
                if (j == width + 1) {
                    System.out.println(this.init_board[i][j]);////////////////////
                } else {
                    System.out.print(this.init_board[i][j]);//////////////
                }
            }
        }
        System.out.println();
    }


    //棋盘边界判断
    public boolean isBoundary(char ch) {
        if (ch != '$' && ch != '@' && ch != '+') {
            return true;
        }else {
            return false;
        }
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


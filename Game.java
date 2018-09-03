import java.util.Random;

public class Game {
    private static final int MINE = -1;
    
    private int m[][] = new int[10][10];
        
    private Game() {        
    }
    
    private static final class SingletonHolder {
        private static final Game SINGLETON = new Game();
    }
    
    public static Game getInstance(){
        return SingletonHolder.SINGLETON;
    }
    
    public void startGame(){
        init();
        generateMines(20);
        calculateMinesProximity();    
    }
    
    public int getCellValue(int i, int j){
        return m[i][j];
    }
    
    private void init(){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = 0;
            }            
        }
    }
    
    private void generateMines(int n){
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            
            if (m[x][y] == MINE) {
                i--;
            }else{
                m[x][y] = MINE;
            }
        }
    }
    
    private void calculateMinesProximity(){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] != MINE) {
                    int noOfMines = 0;
                    noOfMines += isMine(i-1, j-1) ? 1:0;
                    noOfMines += isMine(i,   j-1) ? 1:0;
                    noOfMines += isMine(i+1, j-1) ? 1:0;
                    noOfMines += isMine(i-1, j) ? 1:0;
                    noOfMines += isMine(i+1, j) ? 1:0;
                    noOfMines += isMine(i-1, j+1) ? 1:0;
                    noOfMines += isMine(i,   j+1) ? 1:0;
                    noOfMines += isMine(i+1, j+1) ? 1:0;
                    m[i][j] = noOfMines;                    
                }
            }
        }
    }
    
    private boolean isMine(int i, int j){
        try{
            return m[i][j] == MINE;
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }        
    }
}
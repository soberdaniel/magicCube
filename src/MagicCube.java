import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MagicCube {        //初始化赋值不能写[6][3][3]
    String[][][] threeClassCube= new String[][][]{{{"yellow","yellow","yellow"},{"yellow","yellow","yellow"},{"yellow","yellow","yellow"}},     //正对着自己的面，基准面，正面
        {{"blue","blue","blue"},{"blue","blue","blue"},{"blue","blue","blue"}},     //相对于正面的，上面
        {{"orange","orange","orange"},{"orange","orange","orange"},{"orange","orange","orange"},},     //相对于正面的，右面
        {{"green","green","green"},{"green","green","green"},{"green","green","green"},},     //相对于正面的，下面
        {{"red","red","red"},{"red","red","red"},{"red","red","red"},},     //相对于正面的，左面
        {{"white","white","white"},{"white","white","white"},{"white","white","white"},}};    //相对于正面的，后面

    static String[] relativePostion = new String[]{"正面","上面","右面","下面","左面","后面"};
    MagicCube(){
    }

    MagicCube(String path) throws IOException {
//        FileReader fr = new FileReader("./data.txt");
        FileReader fr = new FileReader(path);
        BufferedReader bfr = new BufferedReader(fr);
        for(int i=0;i<6;i++){
            String faceColor[] = bfr.readLine().split(" ");
            int tmp = 0;
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    this.threeClassCube[i][j][k] = faceColor[tmp];
                    tmp++;
                }
            }
        }
        bfr.close();
    }

    MagicCube(String[][][] threeClassCube){
        this.threeClassCube = threeClassCube;
    }

    MagicCube(MagicCube cube){
        for(int i=0;i<6;i++)
            for(int j=0;j<3;j++)
                for(int k=0;k<3;k++){
                    this.threeClassCube[i][j][k] = cube.threeClassCube[i][j][k];
                }
    }

    void showCube(){
        for(int i=0;i<6;i++){
            System.out.println(relativePostion[i]+(i+1)+"->数组下标"+i);
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    System.out.print(this.threeClassCube[i][j][k]+",");
                }
                 System.out.println();
            }
        }
    }

    boolean isWellDone(){
        String color = null;
        for(int i=0;i<6;i++)
        {
            color = this.threeClassCube[i][0][0];
            for(int j=0;j<3;j++)
            {
                for(int k=0;k<3;k++){
                    if(!this.threeClassCube[i][j][k].equals(color))
                        return false;
                }
            }
        }
       return true;
    }

    //面内顺时针转动
    /*
    a00 a01 a02             a20 a10 a00
    a10 a11 a12     ->      a21 a11 a01
    a20 a21 a22             a22 a12 a02
     */
    public void faceInsideRotate(int faceNum){
        String[][] tmp = this.threeClassCube[faceNum];
        String[][] result = new String[][]{
                {tmp[2][0],tmp[1][0],tmp[0][0]},
                {tmp[2][1],tmp[1][1],tmp[0][1]},
                {tmp[2][2],tmp[1][2],tmp[0][2]},
        };
        this.threeClassCube[faceNum] = result;
    }

    //面内逆时针转动
    public void faceInsideRotateReverse(int faceNum){
        faceInsideRotate(faceNum);
        faceInsideRotate(faceNum);
        faceInsideRotate(faceNum);
    }

    //找到该面相邻的四个面，可以全是顺时针，只需要把序号换一下即可。
    public int[] findNeighborFace(int faceNum){
        switch(faceNum){
            case 0://面1
                return new int[]{1,2,3,4,2};    //正对着自己，顺时针排列,前面四个代表4个面在数组中的下标，最后一个数是要交换的行的下标。
            case 1://面2
                return new int[]{5,2,0,4,0};     //顺时针
            case 2://面3
                return new int[]{1,5,3,0,0};      //
            case 3://面4
                return new int[]{0,2,5,4,2};
            case 4://面5
                return new int[]{1,0,3,5,0};
            case 5://面6
                return new int[]{1,4,3,2,0};
            default:
                return null;
        }
    }

    //顺着面周旋转方向依次找到4个面传入进来即可
public void faceNeighborRowTurnExchange(int faceNum){
    String[][] face1 = this.threeClassCube[findNeighborFace(faceNum)[0]];
    String[][] face2 = this.threeClassCube[findNeighborFace(faceNum)[1]];
    String[][] face3 = this.threeClassCube[findNeighborFace(faceNum)[2]];
    String[][] face4 = this.threeClassCube[findNeighborFace(faceNum)[3]];
    int rowNum = findNeighborFace(faceNum)[4];
    String[] tmp = face4[rowNum];
    face4[rowNum] = face3[rowNum];
    face3[rowNum] = face2[rowNum];
    face2[rowNum] = face1[rowNum];
    face1[rowNum] = tmp;
}
    //面周的改动
    public void faceNeighborRotate(int faceNum){
        switch (faceNum){
            case 0://面1
                faceNeighborRowTurnExchange(faceNum);
                break;
            case 1://面2
                faceInsideRotate(2);
                faceInsideRotateReverse(4);
                faceNeighborRowTurnExchange(faceNum);
                faceInsideRotate(4);
                faceInsideRotateReverse(2);
                break;
            case 2://面3
                faceInsideRotateReverse(1);
                faceInsideRotate(5);
                faceInsideRotate(3);
                faceInsideRotateReverse(0);
                faceNeighborRowTurnExchange(faceNum);
                faceInsideRotate(0);
                faceInsideRotateReverse(3);
                faceInsideRotateReverse(5);
                faceInsideRotate(1);
                break;
            case 3://面4
                faceInsideRotate(2);
                faceInsideRotateReverse(4);
                faceNeighborRowTurnExchange(faceNum);
                faceInsideRotate(4);
                faceInsideRotateReverse(2);
                break;
            case 4://面5
                faceInsideRotate(1);
                faceInsideRotate(0);
                faceInsideRotateReverse(3);
                faceInsideRotateReverse(5);
                faceNeighborRowTurnExchange(faceNum);
                faceInsideRotate(5);
                faceInsideRotate(3);
                faceInsideRotateReverse(0);
                faceInsideRotateReverse(1);
                break;
            case 5://面6
                faceNeighborRowTurnExchange(faceNum);
                break;
        }
    }

    public void faceRotate(int faceNum){
        faceInsideRotate(faceNum);
        faceNeighborRotate(faceNum);
    }
    public void faceRotate(int faceNum,int times){
        for(int i=0;i<times;i++)
            faceRotate(faceNum);
    }
    public void faceRotateReverse(int faceNum){
        faceRotate(faceNum);
        faceRotate(faceNum);
        faceRotate(faceNum);
    }
    public void faceRotateReverse(int faceNum,int times){
        for(int i=0;i<times;i++)
            faceRotateReverse(faceNum);
    }
    
    }

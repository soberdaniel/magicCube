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

    public void faceRotateUnion(int faceNum){
        if(faceNum<6){
            faceRotateReverse(faceNum);
        }
        else{
            faceRotate(faceNum-6);
        }
    }
    public void faceRotateUnionReverse(int faceNum){
        if(faceNum<6){
            faceRotate(faceNum);
        }
        else{
            faceRotateReverse(faceNum-6);
        }
    }

    //写一个评估函数进行评价筛选。
    //统计混乱程度
    public int countWrongColorNum(){
        int[] surfaceCount = new int[6];
        for(int i=0;i<6;i++)
        {
            String rightColor = this.threeClassCube[i][1][1];
            for(int j=0;j<3;j++)
            {
                for(int k=0;k<3;k++)
                {
                    if(!this.threeClassCube[i][j][k].equals(rightColor))
                        surfaceCount[i]++;
                }
            }
        }
        int sum=0;
        for(int i=0;i<surfaceCount.length;i++)
            sum +=surfaceCount[i];
        return sum;
    }

    //第一步先拼好底面四个角块
    public int firstTargetDistance(){
        int[] surfaceCount = new int[6];
        for(int i=0;i<6;i++)
        {
            String rightColor = this.threeClassCube[i][1][1];
            if(i==0){
                if(!this.threeClassCube[i][2][0].equals(rightColor))
                    surfaceCount[i]++;
                if(!this.threeClassCube[i][2][2].equals(rightColor))
                    surfaceCount[i]++;
            }
            if(i==2){
                if(!this.threeClassCube[i][0][2].equals(rightColor))
                    surfaceCount[i]++;
                if(!this.threeClassCube[i][2][2].equals(rightColor))
                    surfaceCount[i]++;
            }
            if(i==5){
                if(!this.threeClassCube[i][2][0].equals(rightColor))
                    surfaceCount[i]++;
                if(!this.threeClassCube[i][2][2].equals(rightColor))
                    surfaceCount[i]++;
            }
            if(i==4){
                if(!this.threeClassCube[i][0][0].equals(rightColor))
                    surfaceCount[i]++;
                if(!this.threeClassCube[i][2][0].equals(rightColor))
                    surfaceCount[i]++;
            }
            if(i==3){
                if(!this.threeClassCube[i][0][0].equals(rightColor))
                    surfaceCount[i]++;
                if(!this.threeClassCube[i][0][2].equals(rightColor))
                    surfaceCount[i]++;
                if(!this.threeClassCube[i][2][0].equals(rightColor))
                    surfaceCount[i]++;
                if(!this.threeClassCube[i][2][2].equals(rightColor))
                    surfaceCount[i]++;
            }
        }
        int sum=0;
        for(int i=0;i<surfaceCount.length;i++)
            sum +=surfaceCount[i];
        return sum;
    }

    //第二步拼好底面一层。
    public int secondTargetDistance(){
        int[] surfaceCount = new int[6];
        for(int i=0;i<6;i++)
        {
            String rightColor = this.threeClassCube[i][1][1];
            if(i==0||i==5){
                for(int j=2;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            surfaceCount[i]++;
                    }
                }
            }
            else if(i==2){
                for(int j=0;j<3;j++)
                {
                    for(int k=2;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            surfaceCount[i]++;
                    }
                }
            }
            else if(i==3){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            surfaceCount[i]++;
                    }
                }
            }
            else if(i==4){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<1;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            surfaceCount[i]++;
                    }
                }
            }
        }
        int sum=0;
        for(int i=0;i<surfaceCount.length;i++)
            sum +=surfaceCount[i];
        return sum;
    }

    //第三步拼好第二层。
    public int thirdTargetDistance(){
        int[] surfaceCount = new int[6];
        for(int i=0;i<6;i++)
        {
            String rightColor = this.threeClassCube[i][1][1];
            if(i==0||i==5){
                for(int j=1;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            surfaceCount[i]++;
                    }
                }
            }
            else if(i==2){
                for(int j=0;j<3;j++)
                {
                    for(int k=1;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            surfaceCount[i]++;
                    }
                }
            }
            else if(i==3){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            surfaceCount[i]++;
                    }
                }
            }
            else if(i==4){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<2;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            surfaceCount[i]++;
                    }
                }
            }
        }
        int sum=0;
        for(int i=0;i<surfaceCount.length;i++)
            sum +=surfaceCount[i];
        return sum;
    }

    //根据索引，以字符串数组形式返回对应棱块的颜色，1号面为黄色，正对着自己，取2号面左边的一个小蓝块为一号位，顺时针1234。竖着的棱，也是取背面左边的楞为5，顺时针5678.
    //1234取蓝色面的颜色为数组第一位，5678按顺时针方向取。
    public int[] getEdgeBlock(int index){
        String[] tmp = new String[2];
        //是不是返回位置更好一点
        int[] position=null;
        switch (index){
            case 1:tmp[0] = this.threeClassCube[1][1][0];
                    tmp[1] = this.threeClassCube[4][1][2];
                    position = new int[]{1,1,0,4,1,2};
                    break;
            case 2:tmp[0] = this.threeClassCube[1][0][1];
                    tmp[1] = this.threeClassCube[5][0][1];
                    position = new int[]{1,0,1,5,0,1};
                    break;
            case 3:tmp[0] = this.threeClassCube[1][1][2];
                tmp[1] = this.threeClassCube[2][1][0];
                position = new int[]{1,1,2,2,1,0};
                break;
            case 4:tmp[0] = this.threeClassCube[1][2][1];
                tmp[1] = this.threeClassCube[0][0][1];
                position = new int[]{1,2,1,0,0,1};
                break;
            case 5:tmp[0] = this.threeClassCube[4][0][1];
                tmp[1] = this.threeClassCube[5][1][2];
                position = new int[]{4,0,1,5,1,2};
                break;
            case 6:tmp[0] = this.threeClassCube[5][1][0];
                tmp[1] = this.threeClassCube[2][0][1];
                position = new int[]{5,1,0,2,0,1};
                break;
            case 7:tmp[0] = this.threeClassCube[2][2][1];
                tmp[1] = this.threeClassCube[0][1][2];
                position = new int[]{2,2,1,0,1,2};
                break;
            case 8:tmp[0] = this.threeClassCube[0][1][0];
                tmp[1] = this.threeClassCube[4][2][1];
                position = new int[]{0,1,0,4,2,1};
                break;
        }
        return position;
    }

    //找到角块的函数，为拼第三层角块服务，只找4个，顺时针，蓝色面，1234，每个角块三个面，以蓝色面为一号面，剩下两个面按顺时针走。
    public int[] getCornerBlock(int index){
        int[] position=null;
        switch (index){
            case 1:
                position = new int[]{1,0,0,4,0,2,5,0,2};
                break;
            case 2:
                position = new int[]{1,0,2,5,0,0,2,0,0};
                break;
            case 3:
                position = new int[]{1,2,2,2,2,0,0,0,2};
                break;
            case 4:
                position = new int[]{1,2,0,0,0,0,4,2,2};
                break;
        }
        return position;
    }

    //计算与目标方块之间的距离，只统计下面两层。距离采用方块颜色不一致的数目表示。
    public int distanceFromTargetSecondFloor(MagicCube targetCube){
        int distance = 0;
        for(int i=0;i<6;i++)
        {
            if(i==0||i==5){
                for(int j=1;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(targetCube.threeClassCube[i][j][k]))
                            distance++;
                    }
                }
            }
            else if(i==2){
                for(int j=0;j<3;j++)
                {
                    for(int k=1;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(targetCube.threeClassCube[i][j][k]))
                            distance++;
                    }
                }
            }
            else if(i==3){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(targetCube.threeClassCube[i][j][k]))
                            distance++;
                    }
                }
            }
            else if(i==4){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<2;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(targetCube.threeClassCube[i][j][k]))
                            distance++;
                    }
                }
            }
        }
        return distance;
    }


    //计算与目标方块之间的距离，只统计下面两层和第三层棱块。距离采用方块颜色不一致的数目表示。
    //需要更改，因为做不到在下面两层不变的情况下。。。
    //变成只统计第三层。所以在角块处也能用。
    public int distanceFromTargetThirdFloorEdgeBlock(MagicCube targetCube){
        int distance = 0;
        for(int i=0;i<6;i++)
        {
            if(i==0||i==5){
                for(int j=0;j<1;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(targetCube.threeClassCube[i][j][k]))
                            distance++;
                    }
                }
            }
            else if(i==2){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<1;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(targetCube.threeClassCube[i][j][k]))
                            distance++;
                    }
                }
            }
            else if(i==4){
                for(int j=0;j<3;j++)
                {
                    for(int k=2;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(targetCube.threeClassCube[i][j][k]))
                            distance++;
                    }
                }
            }
            else if(i==1){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(targetCube.threeClassCube[i][j][k]))
                            distance++;
                    }
                }
            }
        }
        return distance;
    }

    //计算与目标方块之间的距离，只统计下面两层和第三层棱块。距离采用方块颜色不一致的数目表示。
    //需要一个统计棱块是否好了的函数
    public int distanceFromTargetThirdFloorEdgeBlockFinished(){
        int distance = 0;
        for(int i=0;i<6;i++)
        {
            String rightColor = this.threeClassCube[i][1][1];
            if(i==0||i==5){
                for(int j=1;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            distance++;
                    }
                }
                //棱块统计
                if(!this.threeClassCube[i][0][1].equals(rightColor))
                    distance++;
            }
            else if(i==2){
                for(int j=0;j<3;j++)
                {
                    for(int k=1;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            distance++;
                    }
                }
                if(!this.threeClassCube[i][1][0].equals(rightColor))
                    distance++;
            }
            else if(i==3){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            distance++;
                    }
                }
            }
            else if(i==4){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<2;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            distance++;
                    }
                }
                if(!this.threeClassCube[i][1][2].equals(rightColor))
                    distance++;
            }
            //顶面棱块颜色
            else if(i==1){
                if(!this.threeClassCube[i][0][1].equals(rightColor))
                    distance++;
                if(!this.threeClassCube[i][1][0].equals(rightColor))
                    distance++;
                if(!this.threeClassCube[i][1][2].equals(rightColor))
                    distance++;
                if(!this.threeClassCube[i][2][1].equals(rightColor))
                    distance++;
            }
        }
        return distance;
    }

    //计算与目标方块之间的距离，只统计下面两层和第三层顶面。距离采用方块颜色不一致的数目表示。
    public int distanceFromTargetThirdFloorUpperFace(){
        int distance = 0;
        for(int i=0;i<6;i++)
        {
            String rightColor = this.threeClassCube[i][1][1];
            if(i==0||i==5){
                for(int j=1;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            distance++;
                    }
                }
            }
            else if(i==2){
                for(int j=0;j<3;j++)
                {
                    for(int k=1;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            distance++;
                    }
                }
            }
            else if(i==3){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            distance++;
                    }
                }
            }
            else if(i==4){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<2;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            distance++;
                    }
                }
            }
            //顶面
            else if(i==1){
                for(int j=0;j<3;j++)
                {
                    for(int k=0;k<3;k++)
                    {
                        if(!this.threeClassCube[i][j][k].equals(rightColor))
                            distance++;
                    }
                }
            }
        }
        return distance;
    }

    }

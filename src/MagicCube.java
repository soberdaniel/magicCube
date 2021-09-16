import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MagicCube {
    Square[] three_class_cube = new Square[54];     //正方体的边长为6
    Map<String,Square> mapp = new HashMap<>();       //建立一个坐标到Square的映射,方便定位到某个Square.
    MagicCube(){
        int k=0;  //Square数组的下标
        int x,y,z;  //每个正方形中心的空间坐标
        //前 y=0     k取0-8
        for(x=1;x<6;x++,x++){
            for(z=1;z<6;z++,z++){
                three_class_cube[k] = new Square(x,0,z,1);
                k++;
            }
        }
        //后 y=6     k取9-17
        for(x=1;x<6;x++,x++){
            for(z=1;z<6;z++,z++){
                three_class_cube[k] = new Square(x,6,z,2);
                k++;
            }
        }
        //左 x=0     k取18-26
        for(y=1;y<6;y++,y++){
            for(z=1;z<6;z++,z++){
                three_class_cube[k] = new Square(0,y,z,3);
                k++;
            }
        }
        //右 x=6     k取27-35
        for(y=1;y<6;y++,y++){
            for(z=1;z<6;z++,z++){
                three_class_cube[k] = new Square(6,y,z,4);
                k++;
            }
        }
        //下 z=0     k取36-44
        for(x=1;x<6;x++,x++){
            for(y=1;y<6;y++,y++){
                three_class_cube[k] = new Square(x,y,0,5);
                k++;
            }
        }
        //上 z=6     k取45-53
        for(x=1;x<6;x++,x++){
            for(y=1;y<6;y++,y++){
                three_class_cube[k] = new Square(x,y,6,6);
                k++;
            }
        }

        //初始化mapp.
        for(k=0;k<this.three_class_cube.length;k++){
            String tmp = Arrays.toString(this.three_class_cube[k].position);
            this.mapp.put(tmp,this.three_class_cube[k]);
        }
    }

    public Square getSquareFromPosition(int x,int y,int z){
        String tmp = Arrays.toString(new int[]{x,y,z});
        return this.mapp.get(tmp);
    }

    void showCube(){
        int x,y,z,k;
        String color;
        for(k=0;k<54;k++){
            if(k==0) System.out.println("前面");
            if(k==9) System.out.println("后面");
            if(k==18) System.out.println("左面");
            if(k==27) System.out.println("右面");
            if(k==36) System.out.println("下面");
            if(k==45) System.out.println("上面");
            x=this.three_class_cube[k].position[0];
            y=this.three_class_cube[k].position[1];
            z=this.three_class_cube[k].position[2];
            color=Number_to_color.getColor(this.three_class_cube[k].color);
            System.out.println("坐标:"+x+","+y+","+z+","+"颜色:"+color);
        }
    }

    boolean isWellDone(){
        int k,color;
        for(k=0;k<54;){
            color = this.three_class_cube[k].color;
            for(int i=k+1;i<k+9;i++){
                if(this.three_class_cube[i].color!=color)
                    return false;
            }
            k=k+9;
        }
        return true;
    }

    //发现问题，其实转法只有6种，垂直于x轴两种，中间那个面向下转可以堪称是左右两个面向上转，因为全体向上或者向下是不改变的。中间层向下+全体向上=左右向上转。同理垂直y轴两种，z轴两种。
    //也可以按薛哥说的，6个面嘛，6中转法。我这里多写了3种，不过都是if else，你不输入那三种就行了。
    //发现问题,旋转一次不仅仅是改变x=a平面外围的那一圈,x=a平面内本身也改变了.
    //找到一个Square在x=a平面内顺时针旋转90度后的下一个位置对应的Square.从x轴正向往负向看.
    public Square xFindNext(Square square){
        int x = square.position[0];
        int y = square.position[1];
        int z = square.position[2];
        int y_,z_;
        if (y==0){
            y_ = z;
            z_ = 6;
        }
        else if(y==6){
            y_ = z;
            z_ = 0;
        }
        else if(z==0){
            y_ = 0;
            z_ = 6-y;
        }
        else{   //0<y<6,z=6
            y_ = 6;
            z_ = 6-y;
        }
        return getSquareFromPosition(x,y_,z_);
    }

    //开始忘了平面内的方块也会变动.
    public Square xFindNextInside(Square square){
        int x = square.position[0];
        int y = square.position[1];
        int z = square.position[2];
        int y_=0,z_=0;
        if(y==3||z==3){ //面内中间的小方块
            if(y==3){
                y_ = z;
                z_ = y;
            }
            else{
                y_ = 3;
                z_ = 6-y;
            }
        }
        else{   //面内角落的小方块
            if((y==1&&z==5)||(y==5&&z==1)){
                y_=6-y;
                z_=z;
            }
            else{
                y_=y;
                z_=6-z;
            }
        }
        return getSquareFromPosition(x,y_,z_);
    }

    //x=a平面,从x轴正向往负向看,顺时针旋转90度.
    public void xSpin(int a){
        int x;          //当a等于1和5时需要考虑面内的旋转
        if(a==1){
            x=0;
            for(int y=1;y<5;y++,y++){           //取两个方块(0,1,5),(0,3,5)循环旋转交换.
                Square t1 = getSquareFromPosition(x,y,5);
                Square t2 = xFindNextInside(t1);
                Square t3 = xFindNextInside(t2);
                Square t4 = xFindNextInside(t3);
                //旋转后颜色轮换   //必须尾部先移动
                int tmp = t4.color;
                t4.color = t3.color;
                t3.color = t2.color;
                t2.color = t1.color;
                t1.color = tmp;
            }

        }
        else if(a==5){
            x=6;
            for(int y=1;y<5;y++,y++){           //取两个方块(6,1,5),(6,3,5)循环旋转交换.
                Square t1 = getSquareFromPosition(x,y,5);
                Square t2 = xFindNextInside(t1);
                Square t3 = xFindNextInside(t2);
                Square t4 = xFindNextInside(t3);
                //旋转后颜色轮换   //必须尾部先移动
                int tmp = t4.color;
                t4.color = t3.color;
                t3.color = t2.color;
                t2.color = t1.color;
                t1.color = tmp;
            }
        }
        x = a;
        //取x=a,y=0,那一竖的三个小方块,依次循环换位.
        for(int y=5;y>0;y--,y--){
            Square t1 = getSquareFromPosition(x,y,0);
            Square t2 = xFindNext(t1);
            Square t3 = xFindNext(t2);
            Square t4 = xFindNext(t3);
            //旋转后颜色轮换   //必须尾部先移动
            int tmp = t4.color;
            t4.color = t3.color;
            t3.color = t2.color;
            t2.color = t1.color;
            t1.color = tmp;
        }
    }

    //找到一个Square在y=b平面内顺时针旋转90度后的下一个位置对应的Square.从y轴正向往负向看.
    public Square yFindNext(Square square){
        int x = square.position[0];
        int y = square.position[1];
        int z = square.position[2];
        int x_,z_;
        if (x==0){
            x_ = 6-z;
            z_ = 0;
        }
        else if(x==6){
            x_ = 6-z;
            z_ = 6;
        }
        else if(z==0){
            x_ = 6;
            z_ = x;
        }
        else{   //0<x<6,z=6
            x_ = 0;
            z_ = x;
        }
        return getSquareFromPosition(x_,y,z_);
    }

    public Square yFindNextInside(Square square){
        int x = square.position[0];
        int y = square.position[1];
        int z = square.position[2];
        int x_=0,z_=0;
        if(x==3||z==3){ //面内中间的小方块
            if(x==3){
                x_=6-z;
                z_=3;
            }
            else{
                x_ = z;
                z_ = x;
            }
        }
        else{   //面内角落的小方块
            if((x==1&&z==5)||(x==5&&z==1)){
                x_=x;
                z_=6-z;
            }
            else{
                x_=6-x;
                z_=z;
            }
        }
        return getSquareFromPosition(x_,y,z_);
    }

    //y=b平面,从y轴正向往负向看,顺时针旋转90度.
    public void ySpin(int b){
        int y;          //当b等于1和5时需要考虑面内的旋转
        if(b==1){
            y=0;
            for(int x=1;x<5;x++,x++){           //取两个方块(1,0,5),(3,0,5)循环旋转交换.
                Square t1 = getSquareFromPosition(x,y,5);
                Square t2 = yFindNextInside(t1);
                Square t3 = yFindNextInside(t2);
                Square t4 = yFindNextInside(t3);
                //旋转后颜色轮换   //必须尾部先移动
                int tmp = t4.color;
                t4.color = t3.color;
                t3.color = t2.color;
                t2.color = t1.color;
                t1.color = tmp;
            }
        }
        else if(b==5){
            y=6;
            for(int x=1;x<5;x++,x++){           //取两个方块(1,6,5),(3,6,5)循环旋转交换.
                Square t1 = getSquareFromPosition(x,y,5);
                Square t2 = yFindNextInside(t1);
                Square t3 = yFindNextInside(t2);
                Square t4 = yFindNextInside(t3);
                //旋转后颜色轮换   //必须尾部先移动
                int tmp = t4.color;
                t4.color = t3.color;
                t3.color = t2.color;
                t2.color = t1.color;
                t1.color = tmp;
            }
        }
        y = b;
        //取y=b,z=0,那一竖的三个小方块,依次循环换位.
        for(int x=5;x>0;x--,x--){
            Square t1 = getSquareFromPosition(x,y,0);
            Square t2 = yFindNext(t1);
            Square t3 = yFindNext(t2);
            Square t4 = yFindNext(t3);
            //旋转后颜色轮换   //必须尾部先移动
            int tmp = t4.color;
            t4.color = t3.color;
            t3.color = t2.color;
            t2.color = t1.color;
            t1.color = tmp;
        }
    }

    //找到一个Square在z=c平面内顺时针旋转90度后的下一个位置对应的Square.从z轴正向往负向看.
    public Square zFindNext(Square square){
        int x = square.position[0];
        int y = square.position[1];
        int z = square.position[2];
        int x_,y_;
        if (x==0){
            x_ = y;
            y_ = 6;
        }
        else if(x==6){
            x_ = y;
            y_ = 0;
        }
        else if(y==0){
            x_ = 0;
            y_ = 6-x;
        }
        else{   //0<x<6,y=6
            x_ = 6;
            y_ = 6-x;
        }
        return getSquareFromPosition(x_,y_,z);
    }

    public Square zFindNextInside(Square square){
        int x = square.position[0];
        int y = square.position[1];
        int z = square.position[2];
        int x_=0,y_=0;
        if(x==3||y==3){ //面内中间的小方块
            if(x==3){
                x_=y;
                y_=x;
            }
            else{
                x_ = 3;
                y_ = 6-x;
            }
        }
        else{   //面内角落的小方块
            if((x==1&&y==5)||(x==5&&y==1)){
                y_=y;
                x_=6-x;
            }
            else{
                x_=x;
                y_=6-y;
            }
        }
        return getSquareFromPosition(x_,y_,z);
    }

    //z=c平面,从z轴正向往负向看,顺时针旋转90度.
    public void zSpin(int c){
        int z;
        if(c==1){        //当c等于1和5时需要考虑面内的旋转
            z=0;
            for(int x=1;x<5;x++,x++){           //取两个方块(1,5,0),(3,5,0)循环旋转交换.
                Square t1 = getSquareFromPosition(x,5,z);
                Square t2 = zFindNextInside(t1);
                Square t3 = zFindNextInside(t2);
                Square t4 = zFindNextInside(t3);
                //旋转后颜色轮换   //必须尾部先移动
                int tmp = t4.color;
                t4.color = t3.color;
                t3.color = t2.color;
                t2.color = t1.color;
                t1.color = tmp;
            }
        }
        else if(c==5){
            z=6;
            for(int x=1;x<5;x++,x++){           //取两个方块(1,5,6),(3,5,6)循环旋转交换.
                Square t1 = getSquareFromPosition(x,5,z);
                Square t2 = zFindNextInside(t1);
                Square t3 = zFindNextInside(t2);
                Square t4 = zFindNextInside(t3);
                //旋转后颜色轮换   //必须尾部先移动
                int tmp = t4.color;
                t4.color = t3.color;
                t3.color = t2.color;
                t2.color = t1.color;
                t1.color = tmp;
            }
        }
        z=c;
        //取z=c,y=0,那一横的三个小方块,依次循环换位.
        for(int x=5;x>0;x--,x--){
            Square t1 = getSquareFromPosition(x,0,z);
            Square t2 = zFindNext(t1);
            Square t3 = zFindNext(t2);
            Square t4 = zFindNext(t3);
            //旋转后颜色轮换   //必须尾部先移动
            int tmp = t4.color;
            t4.color = t3.color;
            t3.color = t2.color;
            t2.color = t1.color;
            t1.color = tmp;
        }
    }

    //统计每个面上那种颜色的方块的数量最多，并把这些方块的数量加起来。
    public int count_same_color_num(){
        int sum = 0;
        int surface_tag = 0;
        int[] surface = new int[6];
        int[] color_num = new int[7];
        //依次统计前后左右下上
        for(int i=0;i<this.three_class_cube.length+1;i++){
            if(i % 9==0&&i!=0){         //问题在于i最多到53就出去了，没把最后一个面的color_num赋给suface数组,所以加个i等于54的判断和length+1;
                int max = color_num[0];
                for(int k=1;k<color_num.length;k++){
                    if(color_num[k]>max)
                        max = color_num[k];
                }
                surface[surface_tag]=max;
                surface_tag++;
                for(int j=0;j<color_num.length;j++){
                    color_num[j]=0;
                }
            }
            if(i==54)break;
            color_num[this.three_class_cube[i].color]++;
        }
        for(int i=0;i<surface.length;i++)
            sum +=surface[i];
        return sum;
    }
}

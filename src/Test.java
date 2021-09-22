import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Test {
    public static void main(String[] args) throws IOException {
//        MagicCube cube = new MagicCube();
        MagicCube cube = new MagicCube("./data.txt");
        mixUp(cube,2);
//        cube.showCube();
        int index = BFS(cube);
        resolveIndex(index,6);
    }

    public static void mixUp(MagicCube cube,int times){
        Random random = new Random();
        int rotateMethod;
        System.out.println("打乱的方法为：依次按面进行排序(默认顺时针旋转一次)");
        for(int i=0;i<times;i++){
            rotateMethod = random.nextInt(6);
            System.out.print("面"+(rotateMethod+1)+",");
            cube.faceRotate(rotateMethod);
        }
        System.out.print("\n");
    }

    public static int BFS(MagicCube cube){
        List<MagicCube> cubeList = new ArrayList<>();
        cubeList.add(cube);
        int index = 0;
        while(true){
            if((cubeList.get(index)).isWellDone())
            break;
            for(int i=0;i<6;i++){
                cube = new MagicCube(cubeList.get(index));
                cube.faceRotateReverse(i);
                cubeList.add(cube);
            }
            index++;
        }
        System.out.println("索引为："+index);
//        cubeList.get(index).showCube();
        return index;
    }

    public static void resolveIndex(int index,int branch){
        Stack<Integer> st = new Stack<>();
        int sum = index + 1;
        int now_floor = getInteger(Math.log(sum*(branch-1)+1)/Math.log(branch));
        int pre_floor_sum = (int)((Math.pow(branch,now_floor-1)-1)/(branch-1));
        System.out.println("当前所在层数"+now_floor);
        System.out.println("前面所有层的节点总数"+pre_floor_sum);
        int now_floor_serial_num = sum - pre_floor_sum;
        for(int i=1;i<now_floor;i++){
            int last_rotate_face = getFace(now_floor_serial_num%branch);
            now_floor_serial_num = getInteger(now_floor_serial_num/(double)branch);
            st.push(last_rotate_face);
//            System.out.print(last_rotate_face+",");
        }
        while(!st.empty()){
            System.out.print("面"+st.pop()+",");
        }

    }

    public static int getInteger(double num){
        int a = (int)num;
        if(Math.abs(num-a)<1e-6) return a;
        else return a+1;
    }
    public static int getFace(int num){
        if(num == 0) return 6;
        else return num;
    }

}

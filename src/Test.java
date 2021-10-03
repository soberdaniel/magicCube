import java.io.IOException;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
//        MagicCube cube = new MagicCube();
        MagicCube cube = new MagicCube("./data.txt");
        mixUp(cube,100);
        cube.faceRotate(2);
        cube.faceRotate(5);
        cube.faceRotate(1);
        cube.faceRotate(0);
        cube.faceRotate(2);
        cube.faceRotate(2);
        cube.faceRotate(3);
//        cube.showCube();
//        RlFFLrdRlFLr
//        rDRFDf开始想着用交换子来做，算了，还是回归到机器学习。

        //建立一个集合，元素是选中的继承人节点。
        List<MagicCube> cubeRoadToSuccess = new ArrayList<>();
        cubeRoadToSuccess.add(cube);    //初始节点
//        List<Integer> bestIndex = BFS(cubeRoadToSuccess.get(i),cubeRoadToSuccess);
        for(int j=0;j<5;j++){
            int bestIndex = BFS(cubeRoadToSuccess.get(j),cubeRoadToSuccess);
            resolveIndex(bestIndex,12);  //打印出从输入节点到继承人节点的路径
        }
//        cubeRoadToSuccess.get(2).showCube();
//        BFS2(cubeRoadToSuccess);
//        cubeRoadToSuccess.get(3).showCube();
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

    //资源实在有限，无法进行下去，那么必须挑选一部分结点作为继承人。
//    public static List<Integer> BFS(MagicCube cube, List<MagicCube> cubeRoadToSuccess){
//        List<MagicCube> cubeList = new ArrayList<>();
//        cubeList.add(cube);
//        int index = 0;
//        List<Integer> bestIndex = new ArrayList<>();
//        bestIndex.add(0);
////        int flag = 0;
//        while(true){
//            if(cubeList.get(0).isWellDone())break;
//            if(cubeList.get(bestIndex.get(0)).countWrongColorNum()>cubeList.get(index).countWrongColorNum()){
//                bestIndex.clear();
//                bestIndex.add(index);
//            }
//            else if(cubeList.get(bestIndex.get(0)).countWrongColorNum()==cubeList.get(index).countWrongColorNum()){
//                bestIndex.add(index);
//            }
//
//            if((cubeList.get(index)).isWellDone())
//            {
////                flag = 1;
//                System.out.println("success");
//                break;
//            }
//            if(index<55987){    //9331是第七层的初始索引
//                for(int i=0;i<6;i++){
//                    cube = new MagicCube(cubeList.get(index));
//                    cube.faceRotateReverse(i);
//                    cubeList.add(cube);
//                }
//            }
//            index++;
//            if(index >= 335923)   //55987是第八层的初始index
//                break;
//        }
//
//        System.out.println("选中的继承人的索引有"+bestIndex.size()+"个");
//        System.out.println("继承人的混乱程度为："+cubeList.get(bestIndex.get(0)).countWrongColorNum());
////        cubeList.get(bestIndex).showCube();
////        cubeList.get(index).showCube();
//        for(int i=0;i<bestIndex.size();i++)
//        cubeRoadToSuccess.add(cubeList.get(bestIndex.get(i))); //将继承人结点加入到寻找解法路径的列表中去
//        return bestIndex;
//    }

    public static int BFS(MagicCube cube, List<MagicCube> cubeRoadToSuccess){
        Queue<MagicCube> cubeList = new LinkedList<>();
        cubeList.offer(cube);
        int index = 0;
        int bestIndex = 0;
        MagicCube bestCube = cube;
        MagicCube queueHead;
//        int flag = 0;
        while(true){
//            if(cubeList.get(0).isWellDone())  break;
            queueHead = cubeList.element();
            if(bestCube.countWrongColorNum()>queueHead.countWrongColorNum()){
                bestIndex = index;
                bestCube = cubeList.element();
            }
            if(bestCube.isWellDone())
            {
//                flag = 1;
                System.out.println("success");
                break;
            }
            if(index<271453){    //9331是第七层的初始索引
                for(int i=0;i<12;i++){
                    cube = new MagicCube(queueHead);
                    if(i<6)
                    cube.faceRotateReverse(i);  //先逆
                    else
                        cube.faceRotate(i-6);   //后顺
                    cubeList.offer(cube);
                }
            }
            cubeList.poll();
            index++;
            if(index>=3257437)   //55987是第八层的初始index
                break;
        }

        System.out.println("选中的继承人的索引为："+bestIndex);
        System.out.println("继承人的混乱程度为："+bestCube.countWrongColorNum());
//        cubeList.get(bestIndex).showCube();
//        cubeList.get(index).showCube();
        cubeRoadToSuccess.add(bestCube); //将继承人结点加入到寻找解法路径的列表中去
        return bestIndex;
    }

    public static int BFS2(List<MagicCube> cubeRoadToSuccess){
        Queue<MagicCube> cubeList = new LinkedList<>();
        cubeList.offer(cubeRoadToSuccess.get(2));
        MagicCube bestCube = cubeList.element();
        MagicCube queueHead = bestCube;
        int bestCubeWN = bestCube.countWrongColorNum();
        int queueHeadWN;
        int num=0;
//        int flag = 0;
        while(true){
            queueHeadWN = queueHead.countWrongColorNum();
            if(bestCubeWN>queueHeadWN){
                bestCube = queueHead;
                bestCubeWN = queueHeadWN;
            }
            if(bestCube.isWellDone())
            {
//                flag = 1;
                System.out.println("success");
                break;
            }
            if((queueHeadWN<bestCubeWN+20)&&num<240000){
                for(int i=0;i<12;i++){
                    MagicCube cube = new MagicCube(queueHead);
                    if(i<6)
                        cube.faceRotateReverse(i);  //先逆
                    else
                        cube.faceRotate(i-6);   //后顺
                    cubeList.offer(cube);
                }
            }
            cubeList.poll();
            num++;
            if(num>=2425743)   //55987是第八层的初始index
                break;
        }
        System.out.println("继承人的混乱程度为："+bestCubeWN);
//        cubeList.get(bestIndex).showCube();
//        cubeList.get(index).showCube();
        cubeRoadToSuccess.add(bestCube); //将继承人结点加入到寻找解法路径的列表中去
        return num;
    }


    public static void resolveIndex(int index,int branch){
        Stack<Integer> st = new Stack<>();
        int sum = index + 1;
        int now_floor = getInteger(Math.log(sum*(branch-1)+1)/Math.log(branch));
        int pre_floor_sum = (int)((Math.pow(branch,now_floor-1)-1)/(branch-1));
        System.out.println("当前所在层数"+now_floor);
//        System.out.println("前面所有层的节点总数"+pre_floor_sum);
        int now_floor_serial_num = sum - pre_floor_sum;
        for(int i=1;i<now_floor;i++){
            int last_rotate_face = getFace(now_floor_serial_num%branch,branch);
            now_floor_serial_num = getInteger(now_floor_serial_num/(double)branch);
            st.push(last_rotate_face);
//            System.out.print(last_rotate_face+",");
        }
        while(!st.empty()){
            System.out.print("面"+st.pop()+",");
        }
        System.out.println();
    }



    public static int getInteger(double num){
        int a = (int)num;
        if(Math.abs(num-a)<1e-6) return a;
        else return a+1;
    }
    public static int getFace(int num,int branch){
        if(num == 0) return branch;//这个应该返回branch
        else return num;
    }

}

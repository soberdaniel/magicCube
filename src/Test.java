import java.io.IOException;
import java.util.*;

public class Test {
    public static MagicCube targetCube;
    public static List<Thread> threadArray = new ArrayList<>();
    public static void main(String[] args) throws IOException, InterruptedException {
//        MagicCube cube = new MagicCube();
        MagicCube cube = new MagicCube();
//        cube.showCube();
//        System.out.println(cube.secondTargetDistance());
        mixUp(cube,100);
//        cube.faceRotate(1);
//        cube.faceRotateUnion(2);
//        cube.faceRotateUnion(3);
//        cube.showCube();
//        RlFFLrdRlFLr
//        rDRFDf开始想着用交换子来做，算了，还是回归到搜索算法。

        //建立一个集合，元素是选中的继承人节点。
//        List<MagicCube> cubeRoadToSuccess = new ArrayList<>();
//        cubeRoadToSuccess.add(cube);    //初始节点

        Stack<Integer> rotateMethod = new Stack<>();
        for(int target=4;target>=2;target--,target--){
            DFS(target,cube,0,rotateMethod);
        }
        for(int target=2;target>=0;target--){
            DFS(target,cube,0,rotateMethod);
        }
        //到这一行应该拼好四个角块
        for(int target=6;target>=2;target--,target--){
            DFS1(target,cube,0,rotateMethod);
        }
        for(int target=2;target>=0;target--){
            DFS1(target,cube,0,rotateMethod);
        }
        cube.showCube();
        //拼好底面一层
        for(int target=6;target>=2;target--,target--){
            DFS2(target,cube,0,rotateMethod);
        }
        for(int target=2;target>=0;target--){
            DFS2(target,cube,0,rotateMethod);
        }
        cube.showCube();
        //拼好两层
        int stackSize = rotateMethod.size();
        for(int n=0;n<stackSize;n++){
            int method = rotateMethod.pop();
            System.out.print(method+",");
        }
        System.out.println();

//        for(int j=0;j<3;j++){         //广度不太行，不够深入。。。
//            int bestIndex = BFS(cubeRoadToSuccess.get(j),cubeRoadToSuccess);
//            resolveIndex(bestIndex,12);  //打印出从输入节点到继承人节点的路径 //打印出来的面是序号加了1的
//        }
//        for(int j=3;j<9;j++){         //广度不太行，不够深入。。。
//            int bestIndex = BFS2(cubeRoadToSuccess.get(j),cubeRoadToSuccess);
//            resolveIndex(bestIndex,12);  //打印出从输入节点到继承人节点的路径 //打印出来的面是序号加了1的
//        }

//        cubeRoadToSuccess.get(2).showCube();

//        cubei.showCube();
//        System.out.println(cubei.countWrongColorNum());


//        for(int t=1;t<=2;t++){
//            Stack<Integer> rotateMethod = new Stack<>();
//            rotateMethod.push(-11);
//            rotateMethod.push(-11);
//            DFS(8-4*t,cubeRoadToSuccess.get(0),0,rotateMethod);
//            int stackSize = rotateMethod.size();
//            for(int n=0;n<stackSize;n++){
//                int method = rotateMethod.pop();
//                System.out.print(method+",");
//            }
//            System.out.println();
//        }


        //多线程的话试一下
//        for(int x=0;x<12;x++)
//        for(int y=0;y<12;y++){
//            if((y!=(x+6))&&(y!=(x-6))){
//                Stack<Integer> rotateMethod = new Stack<>();
//                MagicCube tmp = new MagicCube(cube);
//                tmp.faceRotateUnion(x);
//                tmp.faceRotateUnion(y);
//                rotateMethod.push(x);
//                rotateMethod.push(y);
//                MultiThread r = new MultiThread(1,tmp,rotateMethod);
//                Thread t = new Thread(r);
//                threadArray.add(t);
//                t.start();
//            }
//        }

//        for(int xx=0;xx<threadArray.size();xx++){
//            threadArray.get(xx).join();
//        }


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
            if(bestCube.firstTargetDistance()>queueHead.firstTargetDistance()){
                bestIndex = index;
                bestCube = queueHead;
            }
            if(bestCube.firstTargetDistance()==0)
            {
//                flag = 1;
                System.out.println("success");
                break;
            }
            if(index<271453){
                for(int i=0;i<12;i++){
                    cube = new MagicCube(queueHead);
//                    if(i<6)
//                    cube.faceRotateReverse(i);  //先逆
//                    else
//                        cube.faceRotate(i-6);   //后顺
                    cube.faceRotateUnion(i);
                    cubeList.offer(cube);
                }
            }
            cubeList.poll();
            index++;
            if(index>=3257437)   //只转了6下。
                break;
        }

        System.out.println("选中的继承人的索引为："+bestIndex);
        System.out.println("继承人的混乱程度为："+bestCube.firstTargetDistance());
//        cubeList.get(bestIndex).showCube();
//        cubeList.get(index).showCube();
        cubeRoadToSuccess.add(bestCube); //将继承人结点加入到寻找解法路径的列表中去
        return bestIndex;
    }


    public static int DFS(int target,MagicCube cube,int depth,Stack<Integer> rotateMethod){
//        Queue<MagicCube> cubeList = new LinkedList<>();
//        cubeList.offer(cubeRoadToSuccess.get(6));
        int nowWN = cube.firstTargetDistance();
        depth++;
        if(nowWN <= target)
        {
            System.out.println("距离为："+nowWN);
            return 1;
        }
        else if(depth>=9)
            return 0;
        int preMethod;
        if(rotateMethod.empty()){
         preMethod = -11;
        }else{
            preMethod = rotateMethod.peek();
        }
        int secondMT;
        if(rotateMethod.size()<2){
            secondMT = -11;
        }else{
            secondMT = rotateMethod.get(rotateMethod.size()-2);
        }
        for(int m=0;m<12;m++){
            if((m!=(preMethod+6))&&(m!=(preMethod-6))&&(!((m==secondMT)&&(m==preMethod)))){
                rotateMethod.push(m);
                cube.faceRotateUnion(m);
                if(DFS(target,cube,depth,rotateMethod)==1)
                    return 1;
                cube.faceRotateUnionReverse(m);
                rotateMethod.pop();
            }
        }
        return 0;
    }

    public static int DFS1(int target,MagicCube cube,int depth,Stack<Integer> rotateMethod){
//        Queue<MagicCube> cubeList = new LinkedList<>();
//        cubeList.offer(cubeRoadToSuccess.get(6));
        int nowWN = cube.secondTargetDistance();
        depth++;
        if(nowWN <= target)
        {
            System.out.println("距离为："+nowWN);
            return 1;
        }
        else if(depth>=9)
            return 0;
        int preMethod;
        if(rotateMethod.empty()){
            preMethod = -11;
        }else{
            preMethod = rotateMethod.peek();
        }
        int secondMT;
        if(rotateMethod.size()<2){
            secondMT = -11;
        }else{
            secondMT = rotateMethod.get(rotateMethod.size()-2);
        }
        for(int m=0;m<12;m++){
            if((m!=(preMethod+6))&&(m!=(preMethod-6))&&(!((m==secondMT)&&(m==preMethod)))){
                rotateMethod.push(m);
                cube.faceRotateUnion(m);
                if(DFS1(target,cube,depth,rotateMethod)==1) //玛德。。。这里原来写的是DFS，我说怎么好奇怪。怎么一下子就算出来了。。。而且打印的cube明显不对。
                    return 1;
                cube.faceRotateUnionReverse(m);
                rotateMethod.pop();
            }
        }
        return 0;
    }

    public static int DFS2(int target,MagicCube cube,int depth,Stack<Integer> rotateMethod){
        int nowWN = cube.thirdTargetDistance();
        depth++;
        if(nowWN <= target)
        {
            System.out.println("距离为："+nowWN);
            return 1;
        }
        else if(depth>=9)
            return 0;
        int preMethod;
        if(rotateMethod.empty()){
            preMethod = -11;
        }else{
            preMethod = rotateMethod.peek();
        }
        int secondMT;
        if(rotateMethod.size()<2){
            secondMT = -11;
        }else{
            secondMT = rotateMethod.get(rotateMethod.size()-2);
        }
        for(int m=0;m<12;m++){
            if((m!=(preMethod+6))&&(m!=(preMethod-6))&&(!((m==secondMT)&&(m==preMethod)))){
                rotateMethod.push(m);
                cube.faceRotateUnion(m);
                if(DFS2(target,cube,depth,rotateMethod)==1)
                    return 1;
                cube.faceRotateUnionReverse(m);
                rotateMethod.pop();
            }
        }
        return 0;
    }

    //拼第三层顶面
    public static int DFS3_1(int target,MagicCube cube,int depth,Stack<Integer> rotateMethod){
        int nowWN = cube.thirdTargetDistance(); //这里改一下
        int nowWWN = cube.countWrongColorNum();
        depth++;
        if(nowWN <= target)
        {
            System.out.println("距离为："+nowWN);
            return 1;
        }
        else if(depth>=9)
            return 0;
        int preMethod;
        if(rotateMethod.empty()){
            preMethod = -11;
        }else{
            preMethod = rotateMethod.peek();
        }
        int secondMT;
        if(rotateMethod.size()<2){
            secondMT = -11;
        }else{
            secondMT = rotateMethod.get(rotateMethod.size()-2);
        }
        for(int m=0;m<12;m++){
            if((m!=(preMethod+6))&&(m!=(preMethod-6))&&(!((m==secondMT)&&(m==preMethod)))){
                rotateMethod.push(m);
                cube.faceRotateUnion(m);
                if((depth>=7)&&(cube.countWrongColorNum()>=nowWWN)){
                    rotateMethod.pop();
                    cube.faceRotateUnionReverse(m);
                }else{
                    if(DFS3_1(target,cube,depth,rotateMethod)==1)
                        return 1;
                    cube.faceRotateUnionReverse(m);
                    rotateMethod.pop();
                }
            }
        }
        return 0;
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

class MultiThread implements Runnable{
    MagicCube cube;
    int target;
    Stack<Integer> rotateMethod;
    MultiThread(int target,MagicCube cube,Stack<Integer> rotateMethod){
        this.target = target;
        this.cube = cube;
        this.rotateMethod = rotateMethod;
    }
    @Override
    public void run() {
        if(Test.DFS(target,cube,0,rotateMethod)==1){
            int stackSize = rotateMethod.size();
            for(int n=0;n<stackSize;n++){
                int method = rotateMethod.pop();
                System.out.print(method+",");
            }
            System.out.println();
            synchronized (Test.targetCube){
                Test.targetCube = this.cube;
                int num = Test.threadArray.size();
                for(int i=0;i<num;i++){
                    if(Thread.currentThread()!=Test.threadArray.get(i)){
                        Test.threadArray.get(i).stop();
                    }
                }
            }
        }
    }
}

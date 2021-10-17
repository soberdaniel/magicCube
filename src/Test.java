import java.io.*;
import java.util.*;

public class Test {
    public static String transfer_num_to_method="furdlbFURDLB";
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
        System.out.println("已拼好四个角块");
        //到这一行应该拼好四个角块

        for(int target=6;target>=2;target--,target--){
            DFS1(target,cube,0,rotateMethod);
        }
        for(int target=2;target>=0;target--){
            DFS1(target,cube,0,rotateMethod);
        }
        System.out.println("已拼好底面一层，打印cube如下所示");
        cube.showCube();
        //到这行拼好底面一层

        System.out.println("拼好第一次层的方法如下所示：");
        //打印拼好第一层的方法，由栈pop出来，从右到左
        int stackSize = rotateMethod.size();
        for(int n=0;n<stackSize;n++){
            int method = rotateMethod.pop();
            System.out.print(method+",");
        }
        System.out.println();

//        for(int target=6;target>=2;target--,target--){
//            DFS2(target,cube,0,rotateMethod);
//        }
//        for(int target=2;target>=0;target--){
//            DFS2(target,cube,0,rotateMethod);
//        }
//        cube.showCube();
        solveSecondFloor("./method.txt",cube);
        System.out.println("已拼好两层，打印cube如下所示");
        cube.showCube();
        //到这行拼好两层

        //发现问题，不能直接先拼好棱块再拼好角块，会卡死，比如拼棱块的时候，只剩两个棱块还没拼好而且顶面都已经是蓝色，你是没办法的，你只能换两个棱块，或者翻转两个。
        //解决办法。。。还是先拼一面。。。虽然不好解释。。。但确实是个办法，还真只能先拼好顶层一面。。。方法已经有了，把棱块角块方法一合并就行。
        //试了几次，一定几率品不好。。。
        solveThirdFloorUpperFace("./method3upperFace.txt",cube);
        System.out.println("已经拼好两层和顶层的一面，打印cube如下所示");
        cube.showCube();

//        System.out.println(cube.distanceFromTargetThirdFloorEdgeBlockFinished());
        solveThirdFloorEdge("./method3edgeInUse.txt",cube);
        System.out.println("已拼好两层和第三层的棱块，打印cube如下所示");
        cube.showCube();
        //到这里应该拼好第三层的棱块

        solveThirdFloorCorner("./method3cornerInUse.txt",cube);
        System.out.println("已完全拼好，打印cube如下所示");
        cube.showCube();
        //到此完全拼好

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

    //读取方法进行转动，按照每行的方法，从左到右，转
    public static int FindBestMethod(String path,int methodNum,MagicCube cube) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader bf = new BufferedReader(fr);

        int bestIndex = 0;
        int index = 0;
        int[] result = new int[methodNum];
        while(true){
            String tmp1 = bf.readLine();
            if(tmp1==null)break;
            List<Integer> rotateMethod = new ArrayList<>();
            String[] tmp2 = tmp1.split(",");
            //将读取到的一行方法，从字符串数组形式转化成int形式
            for(int i=0;i<tmp2.length;i++){
                rotateMethod.add(Integer.parseInt(tmp2[i]));
            }
            MagicCube temp = new MagicCube(cube);
            for(int i=0;i<rotateMethod.size();i++){
                temp.faceRotateUnion(rotateMethod.get(i));
            }
            result[index] = temp.thirdTargetDistance();
            index++;
        }
        for(int i=0;i<result.length;i++){
            if(result[i]<result[bestIndex]){
                bestIndex = i;
            }
        }
        bf.close();
        return bestIndex;
    }

    //java中就没有把函数名作为参数传递的办法吗，这代码重复率太高了。。。
    public static int FindBestMethod_3edge(String path,int methodNum,MagicCube cube) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader bf = new BufferedReader(fr);

        int bestIndex = 0;
        int index = 0;
        int[] result = new int[methodNum];
        while(true){
            String tmp1 = bf.readLine();
            if(tmp1==null)break;
            List<Integer> rotateMethod = new ArrayList<>();
            String[] tmp2 = tmp1.split(",");
            //将读取到的一行方法，从字符串数组形式转化成int形式
            for(int i=0;i<tmp2.length;i++){
                rotateMethod.add(Integer.parseInt(tmp2[i]));
            }
            MagicCube temp = new MagicCube(cube);
            for(int i=0;i<rotateMethod.size();i++){
                temp.faceRotateUnion(rotateMethod.get(i));
            }
            result[index] = temp.distanceFromTargetThirdFloorEdgeBlockFinished();
            index++;
        }
        for(int i=0;i<result.length;i++){
            if(result[i]<result[bestIndex]){
                bestIndex = i;
            }
        }
        bf.close();
        return bestIndex;
    }

    public static int FindBestMethod_3corner(String path,int methodNum,MagicCube cube) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader bf = new BufferedReader(fr);

        int bestIndex = 0;
        int index = 0;
        int[] result = new int[methodNum];
        while(true){
            String tmp1 = bf.readLine();
            if(tmp1==null)break;
            List<Integer> rotateMethod = new ArrayList<>();
            String[] tmp2 = tmp1.split(",");
            //将读取到的一行方法，从字符串数组形式转化成int形式
            for(int i=0;i<tmp2.length;i++){
                rotateMethod.add(Integer.parseInt(tmp2[i]));
            }
            MagicCube temp = new MagicCube(cube);
            for(int i=0;i<rotateMethod.size();i++){
                temp.faceRotateUnion(rotateMethod.get(i));
            }
            result[index] = temp.countWrongColorNum();
            index++;
        }
        for(int i=0;i<result.length;i++){
            if(result[i]<result[bestIndex]){
                bestIndex = i;
            }
        }
        bf.close();
        return bestIndex;
    }

    public static int FindBestMethod_3UpperFace(String path,int methodNum,MagicCube cube) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader bf = new BufferedReader(fr);

        int bestIndex = 0;
        int index = 0;
        int[] result = new int[methodNum];
        while(true){
            String tmp1 = bf.readLine();
            if(tmp1==null)break;
            List<Integer> rotateMethod = new ArrayList<>();
            String[] tmp2 = tmp1.split(",");
            //将读取到的一行方法，从字符串数组形式转化成int形式
            for(int i=0;i<tmp2.length;i++){
                rotateMethod.add(Integer.parseInt(tmp2[i]));
            }
            MagicCube temp = new MagicCube(cube);
            for(int i=0;i<rotateMethod.size();i++){
                temp.faceRotateUnion(rotateMethod.get(i));
            }
            result[index] = temp.distanceFromTargetThirdFloorUpperFace();
            index++;
        }
        for(int i=0;i<result.length;i++){
            if(result[i]<result[bestIndex]){
                bestIndex = i;
            }
        }
        bf.close();
        return bestIndex;
    }

    public static void rotateWithBestMethod(String path,int bestIndex,MagicCube cube) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        int index=0;
        while(true){
            String tmp1 = br.readLine();
            if(tmp1==null)break;
            if(index<bestIndex){
                index++;
                continue;
            }
            else{
                List<Integer> rotateMethod = new ArrayList<>();
                String[] tmp2 = tmp1.split(",");
                //将读取到的一行方法，从字符串数组形式转化成int形式
                for(int i=0;i<tmp2.length;i++){
                    rotateMethod.add(Integer.parseInt(tmp2[i]));
                }
                for(int i=0;i<rotateMethod.size();i++){
                    cube.faceRotateUnion(rotateMethod.get(i));
                }
                break;
            }
        }
    }

    public static void solveSecondFloor(String path,MagicCube cube) throws IOException {
        int initDistance = cube.thirdTargetDistance();
        int nowDistance = initDistance;
        while(nowDistance!=0){
            int bestIndex = FindBestMethod(path,32,cube);
            System.out.println("采用索引"+bestIndex+"的方法");
            rotateWithBestMethod(path,bestIndex,cube);
            nowDistance = cube.thirdTargetDistance();
        }
    }

    public static void solveThirdFloorEdge(String path,MagicCube cube) throws IOException {
        int initDistance = cube.distanceFromTargetThirdFloorEdgeBlockFinished();
        int nowDistance = initDistance;
        int xxx=0;
        while(nowDistance!=0){
            int bestIndex = FindBestMethod_3edge(path,48,cube);
            System.out.println("采用索引"+bestIndex+"的方法");
            rotateWithBestMethod(path,bestIndex,cube);
            nowDistance = cube.distanceFromTargetThirdFloorEdgeBlockFinished();
//            System.out.println(nowDistance);
//            cube.showCube();
            xxx++;
            if(xxx>10){break;}
        }
    }

    public static void solveThirdFloorCorner(String path,MagicCube cube) throws IOException {
        int initDistance = cube.countWrongColorNum();
        int nowDistance = initDistance;
        int xxx = 0;
        while(nowDistance!=0){
            int bestIndex = FindBestMethod_3corner(path,42,cube);
            System.out.println("采用索引"+bestIndex+"的方法");
            rotateWithBestMethod(path,bestIndex,cube);
            nowDistance = cube.countWrongColorNum();
            xxx++;
            if(xxx>10){
                break;
            }
        }
    }

    public static void solveThirdFloorUpperFace(String path,MagicCube cube) throws IOException {
        int initDistance = cube.distanceFromTargetThirdFloorUpperFace();
        int nowDistance = initDistance;
        int xxx=0;
        while(nowDistance!=0){
            int bestIndex = FindBestMethod_3corner(path,90,cube);
            System.out.println("采用索引"+bestIndex+"的方法");
            rotateWithBestMethod(path,bestIndex,cube);
            nowDistance = cube.distanceFromTargetThirdFloorUpperFace();
            xxx++;
            if(xxx>10){
                System.out.println("拼不好了，方法找的还不够多，还需要把一个棱块翻转和一个角块反转的方法找出来。。。");
                break;
            }
        }
    }
}


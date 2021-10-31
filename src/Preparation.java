import java.io.*;
import java.util.*;

//找到方法并写入到method.txt中去
public class Preparation {
    public static String transfer_num_to_method="furdlbFURDLB";
    public static HashSet<MagicCube> cubeStatus = new HashSet<>();  //因为跑出来的方法太多，想试一下区分不同的cube。
    public static void main(String[] args) throws IOException {
        //上面棱块下来的方法，写在method.txt里面
//        for(int i=1;i<5;i++){   //楞i 下到 楞j 的位置，有两种下去的方式，4*4*2=32种
//            for(int j=5;j<9;j++){
//                MagicCube cube = new MagicCube();
//                MagicCube targetCube = createTargetCubeSecondFloorEdgeBlock1(cube,i,j);
//                Stack<Integer> rotateMethod = new Stack<>();
//                DFS_find_method_second_floor_edge(0,cube,targetCube,0,rotateMethod);
//                writeMethod("./method.txt",rotateMethod);
//
//                cube = new MagicCube();
//                targetCube = createTargetCubeSecondFloorEdgeBlock2(cube,i,j);
//                rotateMethod = new Stack<>();
//                DFS_find_method_second_floor_edge(0,cube,targetCube,0,rotateMethod);
//                writeMethod("./method.txt",rotateMethod);
//            }
//        }

        //第三层棱块之间交换的方法，写在method3edge.txt里面
        //不行，做不到在12步之内两个棱块交换位置而另外两个棱块不变。
        //目标拼好第三层，那有没有打乱下面两层，但只交换顶层两个棱块的位置，意思顶层其他块不变的操作。
//        for(int i=1;i<5;i++){   //楞i 与 楞j 交换位置，有两种交换的方式，（3+2+1）*2=12种
//            for(int j=i+1;j<5;j++){
//                MagicCube cube = new MagicCube();
//                MagicCube targetCube = createTargetCubeThirdFloorEdgeBlock1(cube,i,j);
//                Stack<Integer> rotateMethod = new Stack<>();
//                DFS_find_method_third_floor_edge(0,cube,targetCube,0,rotateMethod);
//                writeMethod("./method3edge.txt",rotateMethod);
//
//                cube = new MagicCube();
//                targetCube = createTargetCubeThirdFloorEdgeBlock2(cube,i,j);
//                rotateMethod = new Stack<>();
//                DFS_find_method_third_floor_edge(0,cube,targetCube,0,rotateMethod);
//                writeMethod("./method3edge.txt",rotateMethod);
//            }
//            //楞自身翻转4种
//            MagicCube cube = new MagicCube();
//            MagicCube targetCube = createTargetCubeThirdFloorEdgeBlock2(cube,i,i);
//            Stack<Integer> rotateMethod = new Stack<>();
//            DFS_find_method_third_floor_edge(0,cube,targetCube,0,rotateMethod);
//            writeMethod("./method3edge.txt",rotateMethod);
//        }

        //处理下method3edge.txt，因为它只是保证了第三层，下面两层是乱的，得在用一次复原。中间夹杂1，即顶层的转动
        //可以实现两对棱的翻转或者换位，而整体保持不变。
//        transferMethod3IntoUse("./method3edge.txt","./method3edgeInUse.txt");

//第三层角块之间交换的方法，写在method3corner.txt里面
//        for(int i=1;i<5;i++){   //角块i 与 角块j 交换位置，有三种交换的方式，但觉得有点麻烦，因为棱块自身又有三种翻转方式。
//                                // 交换只写一种吧，翻转弄三种，这样交换 3+2+1=6种，翻转2*4=8种，共14种。
//            for(int j=i+1;j<5;j++){
//                MagicCube cube = new MagicCube();
//                MagicCube targetCube = createTargetCubeThirdFloorCornerBlock1(cube,i,j);
//                Stack<Integer> rotateMethod = new Stack<>();
//                DFS_find_method_third_floor_corner(0,cube,targetCube,0,rotateMethod);
//                writeMethod("./method3corner.txt",rotateMethod);
//            }
//            //楞自身翻转2*4种
//            MagicCube cube = new MagicCube();
//            MagicCube targetCube = createTargetCubeThirdFloorCornerBlock2(cube,i,i);
//            Stack<Integer> rotateMethod = new Stack<>();
//            DFS_find_method_third_floor_corner(0,cube,targetCube,0,rotateMethod);
//            writeMethod("./method3corner.txt",rotateMethod);
//
//            cube = new MagicCube();
//            targetCube = createTargetCubeThirdFloorCornerBlock3(cube,i,i);
//            rotateMethod = new Stack<>();
//            DFS_find_method_third_floor_corner(0,cube,targetCube,0,rotateMethod);
//            writeMethod("./method3corner.txt",rotateMethod);
//
//        }
//        transferMethod3IntoUse("method3corner.txt","method3cornerInUse.txt");

        //按照尹老师的方法对第三层进行一次尝试，寻找原子操作
//        MagicCube test1 = new MagicCube();
//        MagicCube test2 = new MagicCube();
//        Stack<Integer> rotateMethod = new Stack<>();
//        DFS_find_method_third_floor_yin_teacher(0,test1,test2,0,rotateMethod);
        transferYinTeacherMethodInUse("./yinTeacherThirdFloor.txt","./yinTeacherThirdFloorInUse.txt");
    }


    public static void writeMethod(String path, Stack<Integer> st) throws IOException {
        FileWriter wr = new FileWriter(path,true);
        BufferedWriter bw = new BufferedWriter(wr);
        List<Integer> tmp = new ArrayList<>();
        int temp;
        Stack<Integer> newSt = (Stack<Integer>)st.clone();  //不想改变原有的stack，所以拷贝一个。
        while(!newSt.empty()){
            temp = newSt.pop();
            tmp.add(temp);
        }   //  栈内Pop出来的是倒序的，tmp数组0标号是最后一次转的method
        //所以应该把tmp数组反序写入文件
        for(int i=tmp.size()-1;i>=0;i--){
//            bw.write(String.valueOf(tmp.get(i)));   //想改一下不用数字表示，改成下面一行代码
            bw.write(String.valueOf(transfer_num_to_method.charAt(tmp.get(i))));
            if(i==0){
                bw.write("\n");
            }else{
                bw.write(",");
            }
        }
        bw.flush();
        bw.close();
    }

    //给一个原始的cube,比如我想把1号棱块放到5号棱的位置,构造出到达5号楞块之后的cube,不用管第三层。
    public static MagicCube createTargetCubeSecondFloorEdgeBlock1(MagicCube cube,int sourceIndex,int destIndex){
        //假设3，7
        int sposi[] = cube.getEdgeBlock(sourceIndex);   //有两个啊啊啊，前面三个是第一个面的坐标，后面是第二个面的坐标
        int dposi[] = cube.getEdgeBlock(destIndex);
        MagicCube targetCube = new MagicCube(cube);
        targetCube.threeClassCube[dposi[0]][dposi[1]][dposi[2]]=cube.threeClassCube[sposi[0]][sposi[1]][sposi[2]];
        targetCube.threeClassCube[dposi[3]][dposi[4]][dposi[5]]=cube.threeClassCube[sposi[3]][sposi[4]][sposi[5]];

        return targetCube;
    }

    //上面的棱块下来有两种方案，棱块有两个面，面1到面2或者面1到面1。
    public static MagicCube createTargetCubeSecondFloorEdgeBlock2(MagicCube cube,int sourceIndex,int destIndex){
        int sposi[] = cube.getEdgeBlock(sourceIndex);
        int dposi[] = cube.getEdgeBlock(destIndex);
        MagicCube targetCube = new MagicCube(cube);
        targetCube.threeClassCube[dposi[0]][dposi[1]][dposi[2]]=cube.threeClassCube[sposi[3]][sposi[4]][sposi[5]];
        targetCube.threeClassCube[dposi[3]][dposi[4]][dposi[5]]=cube.threeClassCube[sposi[0]][sposi[1]][sposi[2]];

        return targetCube;
    }

    //给一个原始的cube,比如我想把1号棱块与2号棱块交换位置,并保持顶层不变，构造出交换后的目标cube。
    public static MagicCube createTargetCubeThirdFloorEdgeBlock1(MagicCube cube,int sourceIndex,int destIndex){
        int sposi[] = cube.getEdgeBlock(sourceIndex);
        int dposi[] = cube.getEdgeBlock(destIndex);
        MagicCube targetCube = new MagicCube(cube);
        targetCube.threeClassCube[dposi[0]][dposi[1]][dposi[2]]=cube.threeClassCube[sposi[0]][sposi[1]][sposi[2]];
        targetCube.threeClassCube[dposi[3]][dposi[4]][dposi[5]]=cube.threeClassCube[sposi[3]][sposi[4]][sposi[5]];
        targetCube.threeClassCube[sposi[0]][sposi[1]][sposi[2]]=cube.threeClassCube[dposi[0]][dposi[1]][dposi[2]];
        targetCube.threeClassCube[sposi[3]][sposi[4]][sposi[5]]=cube.threeClassCube[dposi[3]][dposi[4]][dposi[5]];
        return targetCube;
    }

   //1号棱块和2号棱块交换有两种换法。
    public static MagicCube createTargetCubeThirdFloorEdgeBlock2(MagicCube cube,int sourceIndex,int destIndex){
        int sposi[] = cube.getEdgeBlock(sourceIndex);
        int dposi[] = cube.getEdgeBlock(destIndex);
        MagicCube targetCube = new MagicCube(cube);
        targetCube.threeClassCube[dposi[0]][dposi[1]][dposi[2]]=cube.threeClassCube[sposi[3]][sposi[4]][sposi[5]];
        targetCube.threeClassCube[dposi[3]][dposi[4]][dposi[5]]=cube.threeClassCube[sposi[0]][sposi[1]][sposi[2]];
        targetCube.threeClassCube[sposi[0]][sposi[1]][sposi[2]]=cube.threeClassCube[dposi[3]][dposi[4]][dposi[5]];
        targetCube.threeClassCube[sposi[3]][sposi[4]][sposi[5]]=cube.threeClassCube[dposi[0]][dposi[1]][dposi[2]];
        return targetCube;
    }

    //exchange corner block
    public static MagicCube createTargetCubeThirdFloorCornerBlock1(MagicCube cube,int sourceIndex,int destIndex){
        int sposi[] = cube.getCornerBlock(sourceIndex);
        int dposi[] = cube.getCornerBlock(destIndex);
        MagicCube targetCube = new MagicCube(cube);
        targetCube.threeClassCube[dposi[0]][dposi[1]][dposi[2]]=cube.threeClassCube[sposi[0]][sposi[1]][sposi[2]];
        targetCube.threeClassCube[dposi[3]][dposi[4]][dposi[5]]=cube.threeClassCube[sposi[3]][sposi[4]][sposi[5]];
        targetCube.threeClassCube[dposi[6]][dposi[7]][dposi[8]]=cube.threeClassCube[sposi[6]][sposi[7]][sposi[8]];
        targetCube.threeClassCube[sposi[0]][sposi[1]][sposi[2]]=cube.threeClassCube[dposi[0]][dposi[1]][dposi[2]];
        targetCube.threeClassCube[sposi[3]][sposi[4]][sposi[5]]=cube.threeClassCube[dposi[3]][dposi[4]][dposi[5]];
        targetCube.threeClassCube[sposi[6]][sposi[7]][sposi[8]]=cube.threeClassCube[dposi[6]][dposi[7]][dposi[8]];
        return targetCube;
    }

    //创造出一个翻转角块的cube
    public static MagicCube createTargetCubeThirdFloorCornerBlock2(MagicCube cube,int sourceIndex,int destIndex){
        int sposi[] = cube.getCornerBlock(sourceIndex);
        int dposi[] = cube.getCornerBlock(destIndex);
        MagicCube targetCube = new MagicCube(cube);
        targetCube.threeClassCube[dposi[0]][dposi[1]][dposi[2]]=cube.threeClassCube[sposi[6]][sposi[7]][sposi[8]];
        targetCube.threeClassCube[dposi[3]][dposi[4]][dposi[5]]=cube.threeClassCube[sposi[0]][sposi[1]][sposi[2]];
        targetCube.threeClassCube[dposi[6]][dposi[7]][dposi[8]]=cube.threeClassCube[sposi[3]][sposi[4]][sposi[5]];
        return targetCube;
    }

    public static MagicCube createTargetCubeThirdFloorCornerBlock3(MagicCube cube,int sourceIndex,int destIndex){
        int sposi[] = cube.getCornerBlock(sourceIndex);
        int dposi[] = cube.getCornerBlock(destIndex);
        MagicCube targetCube = new MagicCube(cube);
        targetCube.threeClassCube[dposi[0]][dposi[1]][dposi[2]]=cube.threeClassCube[sposi[3]][sposi[4]][sposi[5]];
        targetCube.threeClassCube[dposi[3]][dposi[4]][dposi[5]]=cube.threeClassCube[sposi[6]][sposi[7]][sposi[8]];
        targetCube.threeClassCube[dposi[6]][dposi[7]][dposi[8]]=cube.threeClassCube[sposi[0]][sposi[1]][sposi[2]];
        return targetCube;
    }


    public static int DFS_find_method_second_floor_edge(int target,MagicCube cube,MagicCube destCube,int depth,Stack<Integer> rotateMethod){
        int nowWN = cube.distanceFromTargetSecondFloor(destCube);
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
                if(DFS_find_method_second_floor_edge(target,cube,destCube,depth,rotateMethod)==1)
                    return 1;
                cube.faceRotateUnionReverse(m);
                rotateMethod.pop();
            }
        }
        return 0;
    }

    public static int DFS_find_method_third_floor_edge(int target,MagicCube cube,MagicCube destCube,int depth,Stack<Integer> rotateMethod){
        int nowWN = cube.distanceFromTargetThirdFloorEdgeBlock(destCube);
        depth++;
        if(nowWN <= target)
        {
            System.out.println("距离为："+nowWN);
            return 1;
        }
        else if(depth>=12)
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
            if((m!=(preMethod+6))&&(m!=(preMethod-6))&&(!((m==secondMT)&&(m==preMethod)))&&((depth<10)||(nowWN<=13))){
                rotateMethod.push(m);
                cube.faceRotateUnion(m);
                if(DFS_find_method_third_floor_edge(target,cube,destCube,depth,rotateMethod)==1)
                    return 1;
                cube.faceRotateUnionReverse(m);
                rotateMethod.pop();
            }
        }
        return 0;
    }

    public static int DFS_find_method_third_floor_corner(int target,MagicCube cube,MagicCube destCube,int depth,Stack<Integer> rotateMethod){
        int nowWN = cube.distanceFromTargetThirdFloorEdgeBlock(destCube);
        depth++;
        if(nowWN <= target)
        {
            System.out.println("距离为："+nowWN);
            return 1;
        }
        else if(depth>=10)
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
            if((m!=(preMethod+6))&&(m!=(preMethod-6))&&(!((m==secondMT)&&(m==preMethod)))&&((depth<9)||(nowWN<=13))){
                rotateMethod.push(m);
                cube.faceRotateUnion(m);
                if(DFS_find_method_third_floor_corner(target,cube,destCube,depth,rotateMethod)==1)
                    return 1;
                cube.faceRotateUnionReverse(m);
                rotateMethod.pop();
            }
        }
        return 0;
    }

    public static void transferMethod3IntoUse(String path_read,String path_write) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path_read));
        BufferedWriter bw = new BufferedWriter(new FileWriter(path_write));
        while(true){
            String tmp1 = br.readLine();
            if(tmp1==null)break;

//            String tmp2 = new StringBuilder(tmp1).reverse().toString();
//            //不能直接倒过来就用，你得+-6才行。
//            //倒过来。。。也不行，对于一位数是没问题的，11也没问题，但是对于10会出问题，变成01。。。
//            索性不用这个翻转的函数了。

            List<Integer> rotateMethod = new ArrayList<>();
            List<Integer> rotateMethodReverse = new ArrayList<>();
            String[] tmp2 = tmp1.split(",");
            //将字符串数组形式转化成int形式
            for(int i=0;i<tmp2.length;i++){
                int tmp = Integer.parseInt(tmp2[i]);
                rotateMethod.add(tmp);
            }
            for(int i=tmp2.length-1;i>=0;i--){
                int tmp = Integer.parseInt(tmp2[i]);
                if(tmp<6){
                    tmp = tmp + 6;
                }else{
                    tmp = tmp - 6;
                }
                rotateMethodReverse.add(tmp);
            }

            //将list转化成string
            String tmp8 = "";
            for(int i=0;i<rotateMethodReverse.size();i++){
                if(i==rotateMethodReverse.size()-1){
                    tmp8 = tmp8  + rotateMethodReverse.get(i);
                }else{
                    tmp8 = tmp8  + rotateMethodReverse.get(i) + ",";
                }
            }
            for(int i=1;i<4;i++){
                String tmp5 = "";
                String tmp4 = "";
                for(int j=0;j<i;j++){
                    tmp5 = tmp5 + ",1";
                    tmp4 = tmp4 + ",7";
                }
                bw.write(tmp1+tmp5+","+tmp8+tmp4);
                bw.write("\n");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

    //按尹老师的方法再试一次，在拼好两层的基础上找第三层的原子操作
    public static int DFS_find_method_third_floor_yin_teacher(int target,MagicCube cube,MagicCube destCube,int depth,Stack<Integer> rotateMethod) throws IOException {
        int nowWN = cube.distanceFromTargetSecondFloor(destCube);
        int notSame = cube.distanceFromTargetCube(destCube);
        depth++;
        if((nowWN <= target) && (notSame != 0) && (!cubeStatus.contains(cube)))
        {
            System.out.println("get it.");
            return 1;
        }
        else if(depth>=11)
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
            if((m!=(preMethod+6))&&(m!=(preMethod-6))&&(!((m==secondMT)&&(m==preMethod)))&&((depth<9)||(nowWN<=13))){
                rotateMethod.push(m);
                cube.faceRotateUnion(m);
                if(DFS_find_method_third_floor_yin_teacher(target,cube,destCube,depth,rotateMethod)==1){
                    cubeStatus.add(cube);
                    writeMethod("./yinTeacherThirdFloor.txt",rotateMethod);
                }
                cube.faceRotateUnionReverse(m);
                rotateMethod.pop();
            }
        }
        return 0;
    }

    public static void transferYinTeacherMethodInUse(String path_read,String path_write) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(path_read));
        BufferedWriter bw = new BufferedWriter(new FileWriter(path_write));
        String line = null;
        while((line = br.readLine())!=null){
            List<Integer> rotateMethod = new ArrayList();
            String tmp[] = line.split(",");
            for(int i=0;i<tmp.length;i++){
                rotateMethod.add(transfer_num_to_method.indexOf(tmp[i]));
            }
            //将rotateMethod转换成String形式
            String temp = "";
            for(int i=0;i<rotateMethod.size();i++){
                if(i==rotateMethod.size()-1){
                    temp += rotateMethod.get(i) + "\n";
                }
                else{
                    temp += rotateMethod.get(i) + ",";
                }
            }
            bw.write(temp);
        }
        bw.flush();
        bw.close();
        br.close();
    }
}

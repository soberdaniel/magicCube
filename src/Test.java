import java.util.Random;

public class Test {
    public static void main(String[] args){
        MagicCube test = new MagicCube();
//        test.showCube();
//        System.out.println(test.count_same_color_num());
//        System.out.println(test.isWellDone());
//        test.ySpin(1);
//        test.zSpin(1);
        System.out.println(test.count_same_color_num());
//        test.showCube();
        rotate_random(2,test);
        while(!test.isWellDone()){
            int r_method = find_method(test);
            System.out.println("准备采取"+r_method+"目前状态"+test.count_same_color_num());
//            System.out.println(test.count_same_color_num());
            rotate(r_method,test);
        }         //被骗了,玛德,转不回来.
//        test.showCube();
    }

//    public static void rotate_random(int times,MagicCube magicCube){
//        Random r = new Random();
//        for(int i=0;i<times;i++){
//            //总共9种转法,垂直x轴的面3种,a=1,3,5,垂直y轴的面3种,b=1,3,5,垂直z轴的面3种,c=1,3,5.
//            int r_method = r.nextInt(9);
//            switch (r_method){
//                case 0:
//                    magicCube.xSpin(1);
//                    break;
//                case 1:
//                    magicCube.xSpin(3);
//                    break;
//                case 2:
//                    magicCube.xSpin(5);
//                    break;
//                case 3:
//                    magicCube.ySpin(1);
//                    break;
//                case 4:
//                    magicCube.ySpin(3);
//                    break;
//                case 5:
//                    magicCube.ySpin(5);
//                    break;
//                case 6:
//                    magicCube.zSpin(1);
//                    break;
//                case 7:
//                    magicCube.zSpin(3);
//                    break;
//                case 8:
//                    magicCube.zSpin(5);
//                    break;
//            }
//        }
//    }

    //改成6种转法。
public static void rotate_random(int times,MagicCube magicCube){
    Random r = new Random();
    for(int i=0;i<times;i++){
        //总共6种转法,垂直x轴的面2种,a=1,5,垂直y轴的面2种,b=1,5,垂直z轴的面2种,c=1,5。
        int r_method = r.nextInt(6);
        switch (r_method){
            case 0:
                magicCube.xSpin(1);
                break;
            case 1:
                magicCube.xSpin(5);
                break;
            case 2:
                magicCube.ySpin(1);
                break;
            case 3:
                magicCube.ySpin(5);
                break;
            case 4:
                magicCube.zSpin(1);
                break;
            case 5:
                magicCube.zSpin(5);
                break;
        }
    }
}

//如果用color_num 来区分的话有必要弄成12种。
    public static int find_method(MagicCube magicCube){
        int pre_status = magicCube.count_same_color_num();
        int[] after_status = new int[12];       //十二种方法每种有一个status
        for(int r_method=0;r_method<12;r_method++){
            switch (r_method){
                case 0:
                    magicCube.xSpin(1);
                    after_status[0] = magicCube.count_same_color_num();
                    magicCube.xSpin(1);
                    magicCube.xSpin(1);
                    magicCube.xSpin(1);
                    break;
                case 1:
                    magicCube.xSpin(5);
                    after_status[1] = magicCube.count_same_color_num();
                    magicCube.xSpin(5);
                    magicCube.xSpin(5);
                    magicCube.xSpin(5);
                    break;
                case 2:
                    magicCube.ySpin(1);
                    after_status[2] = magicCube.count_same_color_num();
                    magicCube.ySpin(1);
                    magicCube.ySpin(1);
                    magicCube.ySpin(1);
                    break;
                case 3:
                    magicCube.ySpin(5);
                    after_status[3] = magicCube.count_same_color_num();
                    magicCube.ySpin(5);
                    magicCube.ySpin(5);
                    magicCube.ySpin(5);
                    break;
                case 4:
                    magicCube.zSpin(1);
                    after_status[4] = magicCube.count_same_color_num();
                    magicCube.zSpin(1);
                    magicCube.zSpin(1);
                    magicCube.zSpin(1);
                    break;
                case 5:
                    magicCube.zSpin(5);
                    after_status[5] = magicCube.count_same_color_num();
                    magicCube.zSpin(5);
                    magicCube.zSpin(5);
                    magicCube.zSpin(5);
                    break;
                case 6:
                    magicCube.xSpin(1);
                    magicCube.xSpin(1);
                    magicCube.xSpin(1);
                    after_status[6] = magicCube.count_same_color_num();
                    magicCube.xSpin(1);
                    break;
                case 7:
                    magicCube.xSpin(5);
                    magicCube.xSpin(5);
                    magicCube.xSpin(5);
                    after_status[7] = magicCube.count_same_color_num();
                    magicCube.xSpin(5);
                    break;
                case 8:
                    magicCube.ySpin(1);
                    magicCube.ySpin(1);
                    magicCube.ySpin(1);
                    after_status[8] = magicCube.count_same_color_num();
                    magicCube.ySpin(1);
                    break;
                case 9:
                    magicCube.ySpin(5);
                    magicCube.ySpin(5);
                    magicCube.ySpin(5);
                    after_status[9] = magicCube.count_same_color_num();
                    magicCube.ySpin(5);
                    break;
                case 10:
                    magicCube.zSpin(1);
                    magicCube.zSpin(1);
                    magicCube.zSpin(1);
                    after_status[10] = magicCube.count_same_color_num();
                    magicCube.zSpin(1);
                    break;
                case 11:
                    magicCube.zSpin(5);
                    magicCube.zSpin(5);
                    magicCube.zSpin(5);
                    after_status[11] = magicCube.count_same_color_num();
                    magicCube.zSpin(5);
                    break;
            }
        }
            int max = 0;
            for(int i=1;i<after_status.length;i++){
                if(after_status[i]>after_status[max])
                    max = i;
            }
            return max;
        }

    public static void rotate(int r_method,MagicCube magicCube){
            //总共6种转法,垂直x轴的面2种,a=1,5,垂直y轴的面2种,b=1,5,垂直z轴的面2种,c=1,5。
            switch (r_method) {
                case 0:
                    magicCube.xSpin(1);
                    break;
                case 1:
                    magicCube.xSpin(5);
                    break;
                case 2:
                    magicCube.ySpin(1);
                    break;
                case 3:
                    magicCube.ySpin(5);
                    break;
                case 4:
                    magicCube.zSpin(1);
                    break;
                case 5:
                    magicCube.zSpin(5);
                    break;
                case 6:
                    magicCube.xSpin(1);
                    magicCube.xSpin(1);
                    magicCube.xSpin(1);
                    break;
                case 7:
                    magicCube.xSpin(5);
                    magicCube.xSpin(5);
                    magicCube.xSpin(5);
                    break;
                case 8:
                    magicCube.ySpin(1);
                    magicCube.ySpin(1);
                    magicCube.ySpin(1);
                    break;
                case 9:
                    magicCube.ySpin(5);
                    magicCube.ySpin(5);
                    magicCube.ySpin(5);
                    break;
                case 10:
                    magicCube.zSpin(1);
                    magicCube.zSpin(1);
                    magicCube.zSpin(1);
                    break;
                case 11:
                    magicCube.zSpin(5);
                    magicCube.zSpin(5);
                    magicCube.zSpin(5);
                    break;
            }
    }
}

import java.util.Random;

public class Test {
    public static void main(String[] args){
        MagicCube test = new MagicCube();
        test.showCube();
//        System.out.println(test.isWellDone());
        test.ySpin(1);
        test.zSpin(1);
        test.showCube();
//        rotate_random(10,test);
        test.zSpin(1);
        test.zSpin(1);
        test.zSpin(1);
        test.ySpin(1);
        test.ySpin(1);
        test.ySpin(1);
//        while(!test.isWellDone()){
//            rotate_random(test);
//        }         //被骗了,玛德,转不回来.
        test.showCube();
    }

    public static void rotate_random(int times,MagicCube magicCube){
        Random r = new Random();
        for(int i=0;i<times;i++){
            //总共9种转法,垂直x轴的面3种,a=1,3,5,垂直y轴的面3种,b=1,3,5,垂直z轴的面3种,c=1,3,5.
            int r_method = r.nextInt(9);
            switch (r_method){
                case 0:
                    magicCube.xSpin(1);
                    break;
                case 1:
                    magicCube.xSpin(3);
                    break;
                case 2:
                    magicCube.xSpin(5);
                    break;
                case 3:
                    magicCube.ySpin(1);
                    break;
                case 4:
                    magicCube.ySpin(3);
                    break;
                case 5:
                    magicCube.ySpin(5);
                    break;
                case 6:
                    magicCube.zSpin(1);
                    break;
                case 7:
                    magicCube.zSpin(3);
                    break;
                case 8:
                    magicCube.zSpin(5);
                    break;
            }
        }
    }
}

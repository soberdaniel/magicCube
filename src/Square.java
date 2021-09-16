import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Square{
    public int[] position = new int[3]; //6个面，每个面9个小正方形,每个小正方形的中心的坐标叫做position，取每个小正方形的边长为2
    public int color;       //每个小正方形的颜色 取值1-6，red,green,blue,orange,yellow,pink

    Square(int x,int y,int z,int color){
        this.color = color;
        this.position[0] = x;
        this.position[1] = y;
        this.position[2] = z;
    }
}

class Number_to_color{
    static Map<String,String> map = new HashMap<>();
    static{
        map.put("1","red");
        map.put("2","orange");
        map.put("3","white");
        map.put("4","yellow");
        map.put("5","green");
        map.put("6","blue");
    }
    public static String getColor(int num){
        return map.get(String.valueOf(num));
    }
}


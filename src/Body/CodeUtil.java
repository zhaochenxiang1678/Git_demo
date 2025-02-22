package Body;

import java.util.Random;

public class CodeUtil {
    public static String getCode()
    {
        String[] arr={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        String result="";
        Random r=new Random();
        for(int i=0;i<4;i++)
        {
            int index=r.nextInt(arr.length);
            result+=arr[index];
        }
        int number=r.nextInt(10);
        result+=number;
        return result;
    }
}

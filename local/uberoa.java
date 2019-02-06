import java.util.Arrays;

class uberoa {
    public static void main(String[] args) {
        for (String s : sort(new String[]{"aa", "ab", "ad", "aabc"})) {
            System.out.println(s);
        }
    }
    public static String[] sort(String[] arr) {
        Arrays.sort(arr);
        return arr;
    }



//     input：
// boa，chase，100（代表boa给chase打了100刀）
// chase，boa，200
// boa，wellsfargo，300
// wellsfargo，chase，400
// chase，wellsfargo ，150
// 输出
// boa，chase，100（代表boa最后收到了100，而不是chase，boa，-100，因为要求数字是正数）
// wellsfargo，boa，300
// chase，wellsfargo，250
}
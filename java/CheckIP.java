import java.util.regex.*;
class Solution {
    String ipv4 = "IPv4";
    String ipv6 = "IPv6";
    String neither = "Neither";
    public String checkIPV6(String queryIP) {
        String[] arr = queryIP.split(":");
        if(arr.length != 8) {
            return neither;
        }
        for(int i=0;i<8;i++) {
            if(!Pattern.matches("^[0-9A-Fa-f]{1,4}+$", arr[i])) {
                return neither;
            }
        }
        return ipv6;
    }

    public String checkIPV4(String queryIP) {
        String[] arr = queryIP.split("\\.");
        if(arr.length != 4) {
            System.out.println("kkk");
            return neither;
        }
        try{
            for(int i=0;i<4;i++) {
                if(arr[i].length()>1 && arr[i].charAt(0) == '0' ) {

                    return neither;
                }
                int num = Integer.parseInt(arr[i]);
                if(num>255) return neither;
            }
        } catch(Exception e) {
            System.out.println("tt");
            return neither;
        }
        System.out.println("kkk");
        return ipv4;
    }

    public String validIPAddress(String queryIP) {

        if(queryIP.contains(".")) {
            return checkIPV4(queryIP);
        } else {
            return checkIPV6(queryIP);
        }
    }

    public static void main(String[] args) {
        Solution o=new Solution();
        System.out.println(o.validIPAddress("172.16.254.1"));;
    }
}
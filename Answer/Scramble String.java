/**
 * 本代码由九章算法编辑提供。没有版权欢迎转发。
 * - 九章算法致力于帮助更多中国人找到好的工作，教师团队均来自硅谷和国内的一线大公司在职工程师。
 * - 现有的面试培训课程包括：九章算法班，系统设计班，BAT国内班
 * - 更多详情请见官方网站：http://www.jiuzhang.com/
 */

public class Solution {
    /*
       Second method: comes up with DP naturally
       f[n][i][j] means isScramble(s1[i: i+n], s2[j: j+n])
       f[n][i][j] = f[k][i][j] && f[n - k][i+k][j+k]
                   || f[k][i][j+n-k] && f[n-k][i+k][j]
  
   */
    
     public boolean isScramble(String s1, String s2) {
        if( s1.length() != s2.length() ){
            return false;
        }
        
        if( s1.length() == 0 || s1.equals(s2)) {
            return true;
        }
        
        int n = s1.length();
        boolean[][][] rst = new boolean[n][n][n];
        for(int i=0; i< n; i++){
            for(int j=0;j<n; j++){
                rst[0][i][j] = s1.charAt(i) == s2.charAt(j);
            }
        }
        
        for(int len = 2; len <= n; len++){
            for(int i = n - len; i>= 0; i--) {
                for(int j = n - len;  j>=0; j--){
                    boolean r = false;
                    for(int k = 1; k < len && r == false; k++){
                        r = (rst[k-1][i][j] && rst[len-k-1][i+k][j+k]) || (rst[k-1][i][j+len-k] && rst[len-k-1][i+k][j]);
                    }
                    rst[len-1][i][j] = r;
                }
            }
        }
        
        return rst[n-1][0][0];
     }
 }
    
 
public class Solution {
    /**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
    public boolean isScramble(String s1, String s2) {
        // Write your code here
        if (s1.length() != s2.length()) {
            return false;
        }
        
        if (s1.length() == 0 || s1.equals(s2)) {
            return true;
        }
        
        if (!isValid(s1, s2)) {
            return false;
        }// Base Cases
        
        
        for (int i = 1; i < s1.length(); i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, s1.length());
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, s2.length());
            String s23 = s2.substring(0, s2.length() - i);
            String s24 = s2.substring(s2.length() - i, s2.length());
            
            if (isScramble(s11, s21) && isScramble(s12, s22)) return true;
            if (isScramble(s11, s24) && isScramble(s12, s23)) return true;// cut 
            
        }
        return false;
    }
    
    
    private boolean isValid(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        if (!(new String(arr1)).equals(new String(arr2))) {
            return false;
        }
        return true;
    }
}


public class Solution {
    /**
     * @param s1 A string
     * @param s2 Another string
     * @return whether s2 is a scrambled string of s1
     */
     
     private boolean checkScramble(String s1,int start1, String s2, int start2, int k, int [][][]visit) {
		if(visit[start1][start2][k] == 1)
            return true;
        if(visit[start1][start2][k] ==-1)
            return false;
        
        
        if (s1.length() != s2.length()) {
            visit[start1][start2][k] = -1;
            return false;
        }
        
        if (s1.length() == 0 || s1.equals(s2)) {
            visit[start1][start2][k] = 1;
            return true;
        }
        
        if (!isValid(s1, s2)) {
            visit[start1][start2][k] = -1;
            return false;
        }// Base Cases
        
        
        for (int i = 1; i < s1.length(); i++) {
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i, s1.length());
            
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i, s2.length());
            
            String s23 = s2.substring(0, s2.length() - i);
            String s24 = s2.substring(s2.length() - i, s2.length());
            
            if (checkScramble(s11,start1, s21, start2, i, visit) && checkScramble(s12, start1+i, s22, start2+i,k-i, visit))  {
                visit[start1][start2][k] = 1;
                return true;
            }
            
            if (checkScramble(s11,start1, s24, start2+k-i, i, visit) && checkScramble(s12,start1+i, s23,start2, k-i, visit))
            {
                visit[start1][start2][k] = 1;
                return true;
            }
        }
        visit[start1][start2][k] = -1;
        return false;
    }
    public boolean isScramble(String s1, String s2) {
        int len = s1.length();
        int [][][] visit = new int[len][len][len + 1];
        return checkScramble(s1,0,s2,0, len, visit);
    }
    
    
    private boolean isValid(String s1, String s2) {
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        if (!(new String(arr1)).equals(new String(arr2))) {
            return false;
        }
        return true;
    }
}

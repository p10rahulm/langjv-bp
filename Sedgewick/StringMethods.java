public class StringMethods {
    public static void main(String[] args){
        String s = "Hello, how are you";
        StdOut.printf("The string %s is %sa palindrome.\n",s,(isPalindrome(s)?"":"not "));
        VectorOps.printvector(split_by_whitespace(s));
        StdOut.printf("The string \"%s\" is %salphabetically sorted.\n",s,(check_string_alphabetically_sorted(split_by_whitespace(s))?"":"not "));
        StdOut.println(reverse_string(s));
        s = "hi.txt";
        StdOut.printf("The file %s has filename base \"%s\" and extension \"%s\".\n",s,filename_extension(s)[0],filename_extension(s)[1]);
        s = "http://www.yoyoma.com";
        StdOut.printf("The string %s is %sa link.\n",s,(InternetLinks.islink(s)?"":"not "));
        StdOut.println("A compared to B is = "+"A".compareTo("B"));
        StdOut.println(reverse_string("Hi Hello how are you?"));
        StdOut.println(entropy("Hi Hello how are you?"));
        StdOut.println(entropy("My name is Anthony Gonzalves"));
        StdOut.println(entropy("The quick Brown Fox Jumped Over the lazy Dog 012345789"));
    }
    private static int[] fill_buckets_for_entropy(String s){
        s = s.toLowerCase();
        String non_alpha_numeric = "[^a-z0-9]";
        s = s.replaceAll(non_alpha_numeric,"");
        char[] chararray = s.toCharArray();
        int[] freq = new int[36];//26 chars and 10 numerics
        for (int i = 0; i < chararray.length; i++) {
            int charbucket = ((int)(chararray[i]-'0') >10)?(int)(chararray[i]-'a'+10):(int)(chararray[i]-'0');
            freq[charbucket]+=1;
        }
        return freq;
    }
    public static double entropy(String s){
        int[] freq = fill_buckets_for_entropy(s);
        double entropy = 0;
        for (int i = 0; i < freq.length; i++) {
            if(freq[i]>0) {
                double prob_c = (double) freq[i] / s.length();
                entropy -= prob_c * MMath.log_base(prob_c, 2);
            }
        }
        return entropy;

    }
    public static boolean isPalindrome(String s){
        int sl =s.length();
        boolean out = true;
        for (int i = 0; i < sl; i++) {
            if(s.charAt(sl-1-i)!=s.charAt(i)){
                out = false;
                break;
            }
        }
        return out;
    }
    public static String[] filename_extension(String s){
        //split string into filename base and extension
        String[] out = new String[2];
        int dotn = s.indexOf(".");
        String filename = s.substring(0,dotn);
        String extension = s.substring(dotn,s.length());
        out[0] = filename;
        out[1] = extension;
        return out;
    }
    public static void print_lines_from_stdin_containing_string(String s){
        while(!StdIn.isEmpty()){
            String p = StdIn.readLine();
            if(p.contains(s))System.out.println(p);
        }
    }
    public static String[] split_by_whitespace(String s){
        return s.split("\\s+");
    }
    public static boolean check_string_alphabetically_sorted(String[] s_array){
        //checking for ascending alphabetically. ie true for the sting array ["A","B","C"...]
        for (int i = 1; i < s_array.length; i++) {
            if(s_array[i-1].compareTo(s_array[i])>0){
                return false;
            }
        }
        return true;
    }
    public static String reverse_string(String s){
        int n = s.length();
        if(n<=1)return s;
        String a = s.substring(0,n/2);
        String b = s.substring(n/2,n);
        return (reverse_string(b)+reverse_string(a));
    }

}

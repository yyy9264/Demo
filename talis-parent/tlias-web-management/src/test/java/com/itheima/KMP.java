package com.itheima;

public class KMP {
    public static int[] getNext(String pattern){
        int[] next=new int[pattern.length()];
        next[0]=0;
        //j记录前缀串的末尾
        int j=0;
        //i记录后缀串的末尾
        for(int i=1;i<pattern.length();i++){
            while(j>0&&pattern.charAt(j)!=pattern.charAt(i)){
                j=next[j-1];
            }
            if(pattern.charAt(i)==pattern.charAt(j)){
                j++;
            }
            next[i]=j;
        }
        return next;
    }

    public static void main(String[] args) {
        String s="aabaaf";
        int[] next=getNext(s);
        for(int i:next){
            System.out.print(i+" ");
        }
    }
}

package com.bloomfilter.java;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author huanyao
 * @version 1.0
 * @ClassName BloomFilterTest.java
 * @Description TODO(用一句话描述该文件做什么)
 * @date 2021/3/31 5:34 下午
 */
public class BloomFilterTest {
    private static int size=1000000;//预计要插入的数据量
    private static float fpp=0.001f;//期望的误判率
    public static void main(String[] args) {
        BloomFilter<CharSequence> bloomFilterStr = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), size, fpp);
        for (int i = 0; i <= size; i++) {
            bloomFilterStr.put("test" + i);
        }

        System.out.println(bloomFilterStr.mightContain("test1"));
        System.out.println(bloomFilterStr.mightContain("test2"));
        System.out.println(bloomFilterStr.mightContain("test1000001"));

        int errCount = 0;
        for (int i = size+1; i < size+1000000; i++) {
            String value = "test" + i;
            if (bloomFilterStr.mightContain(value)) {
                errCount++;
                System.out.println(value+"误判了");
            }
        }
        System.out.println("误判了"+errCount+"个数据");

    }
}

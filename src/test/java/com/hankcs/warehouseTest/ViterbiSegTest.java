package com.hankcs.warehouseTest;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.io.IOUtil;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import junit.framework.TestCase;

/**
 * Created by root on 16-10-10.
 */
public class ViterbiSegTest extends TestCase {

    public void testCompanyNameSeg(){
        IOUtil.isResourceParam = false;
        HanLP.Config.BiGramDictionaryPath = "data/dictionary/CoreNatureDictionary.ngram.mini.txt";
        HanLP.Config.CoreDictionaryPath = "warehouse/brand_use/data/dictionary/CoreNatureDictionary.mini.txt";
        // 注意品牌词不能是单个字，否则会报错
        HanLP.Config.CustomDictionaryPath = new String[]{
                "warehouse/company_name/data/dictionary/custom/CustomDictionary.txt",
                "warehouse/brand_use/data/dictionary/custom/上海地名.txt ns", "warehouse/brand_use/data/dictionary/custom/全国地名大全.txt ns",
                "warehouse/brand_use/data/dictionary/custom/品牌词_不带空格.txt bn", "warehouse/brand_use/data/dictionary/custom/深圳地名.txt ns",
                "warehouse/brand_use/data/dictionary/custom/二级品牌名.txt bn"};
        StandardTokenizer.SEGMENT.enableNameRecognize(false);
        System.out.println(StandardTokenizer.segment("北京英思杰科技有限公司深圳分公司"));
        System.out.println(StandardTokenizer.segment("深圳市康力欣电子有限公司"));
    }

    public void testGetWordSpeech(){
        HanLP.Config.CoreDictionaryPath = "warehouse/brand_use/data/dictionary/CoreNatureDictionary.mini.txt";
        HanLP.Config.CustomDictionaryPath = new String[]{"warehouse/company_name/data/dictionary/custom/CustomDictionary.txt"};
        System.out.println(CustomDictionary.get("赛格力"));
        System.out.println(CoreDictionary.get("赛格力"));
        System.out.println(StandardTokenizer.segment("深圳市赛格力数码科技有限公司"));
    }
}

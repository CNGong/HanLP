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
        // 注意品牌词不能是单个字，否则会报错
        HanLP.Config.CustomDictionaryPath = new String[]{
                "warehouse/company_name/data/dictionary/custom/CustomDictionary.txt","warehouse/brand_use/data/dictionary/custom/CustomDictionary.txt",
                "warehouse/brand_use/data/dictionary/custom/上海地名.txt ns", "warehouse/brand_use/data/dictionary/custom/全国地名大全.txt ns",
                "warehouse/brand_use/data/dictionary/custom/品牌词_不带空格.txt bn", "warehouse/brand_use/data/dictionary/custom/深圳地名.txt ns",
                "warehouse/brand_use/data/dictionary/custom/二级品牌名.txt bn","warehouse/company_name/data/dictionary/custom/公司名后缀.txt nis"};
        StandardTokenizer.SEGMENT.enableNameRecognize(false);
        System.out.println(StandardTokenizer.segment("上汽"));
        System.out.println(StandardTokenizer.segment("深圳市康力欣电子有限公司"));
    }

    public void testBrandUseSeg(){
        IOUtil.isResourceParam = false;
        // 注意品牌词不能是单个字，否则会报错
        HanLP.Config.CustomDictionaryPath = new String[]{
                "warehouse/brand_use/data/dictionary/custom/CustomDictionary.txt",
                "warehouse/brand_use/data/dictionary/custom/上海地名.txt ns", "warehouse/brand_use/data/dictionary/custom/全国地名大全.txt ns",
                "warehouse/brand_use/data/dictionary/custom/品牌词_不带空格.txt bn", "warehouse/brand_use/data/dictionary/custom/深圳地名.txt ns",
                "warehouse/brand_use/data/dictionary/custom/二级品牌名.txt bn"};
        StandardTokenizer.SEGMENT.enableNameRecognize(false);
        System.out.println(StandardTokenizer.segment("上汽"));
        System.out.println(StandardTokenizer.segment("深圳市康力欣电子有限公司"));
    }

    public void testGetWordSpeech(){
        HanLP.Config.CoreDictionaryPath = "data/dictionary/CoreNatureDictionary.txt";
        HanLP.Config.BiGramDictionaryPath = "data/dictionary/CoreNatureDictionary.ngram.txt";
        HanLP.Config.CustomDictionaryPath = new String[]{"warehouse/company_name/data/dictionary/custom/CustomDictionary.txt"};
        System.out.println(CustomDictionary.get("联手"));
        System.out.println(CoreDictionary.get("联手"));
//        StandardTokenizer.SEGMENT.enableNumberQuantifierRecognize(false).enableNameRecognize(false);
        System.out.println(StandardTokenizer.segment("目前公司是Oracle、Sun、IBM、HP、Aspen 、Labsystems、Honeywell、Simsci、SilverStream、Cisco、3Com、Netscreen、Avaya、AMP、Netscreen、CA等国际着名IT公司的国内或行业指定代理商"));
    }

    public void testInsert(){
        HanLP.Config.CoreDictionaryPath = "data/dictionary/CoreNatureDictionary.txt";
        HanLP.Config.BiGramDictionaryPath = "data/dictionary/CoreNatureDictionary.ngram.txt";
        HanLP.Config.CustomDictionaryPath = new String[]{"warehouse/company_name/data/dictionary/custom/CustomDictionary.txt"};
        System.out.println(CustomDictionary.get("黄金周"));
        System.out.println(CustomDictionary.contains("黄金周"));
//        CustomDictionary.insert("黄金周","nz 1000");
//        CustomDictionary.remove("黄金周");
        CustomDictionary.insertIntoTrie("黄金周","nz 1000");
        System.out.println(StandardTokenizer.segment("黄金周出行，人山人海"));
        CustomDictionary.remove("黄金周");
        System.out.println(StandardTokenizer.segment("黄金周出行，人山人海"));

    }
}

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
        HanLP.Config.CoreDictionaryPath = "data/dictionary/CoreNatureDictionary.txt";
        HanLP.Config.BiGramDictionaryPath = "data/dictionary/CoreNatureDictionary.ngram.txt";
        StandardTokenizer.SEGMENT.enableNameRecognize(false).enablePlaceRecognize(true);
        System.out.println(StandardTokenizer.segment("上汽"));
        System.out.println(StandardTokenizer.segment("河南车宝行汽车销售有限公司成立于2015年4月，位于三全路中州大道一米阳光，附近有风雅颂、琥珀名城等小区及柳林等城中村，住宿便利。"));
    }

    public void testGetWordSpeech(){
        IOUtil.isResourceParam = false;
        HanLP.Config.CoreDictionaryPath = "data/dictionary/CoreNatureDictionary.txt";
        HanLP.Config.BiGramDictionaryPath = "data/dictionary/CoreNatureDictionary.ngram.txt";
        HanLP.Config.CustomDictionaryPath = new String[]{"warehouse/brand_use/data/dictionary/custom/CustomDictionary.txt"};
        System.out.println(CustomDictionary.get("路中"));
        System.out.println(CoreDictionary.get("路中"));
//        StandardTokenizer.SEGMENT.enableNumberQuantifierRecognize(false).enableNameRecognize(false);
        System.out.println(StandardTokenizer.segment("河南车宝行汽车销售有限公司成立于2015年4月，位于三全路中州大道一米阳光，附近有风雅颂、琥珀名城等小区及柳林等城中村，住宿便利。"));
        System.out.println(StandardTokenizer.segment("2008年雪润全国三十余家经销商举行第一次例会，共同分享了雪润产品带给大家以及顾客内在美的改变"));
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

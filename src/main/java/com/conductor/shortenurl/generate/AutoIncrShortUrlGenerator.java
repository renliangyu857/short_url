//package com.conductor.shortenurl.generate;
//
//import com.baidu.fsg.uid.UidGenerator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AutoIncrShortUrlGenerator implements ShortUrlGenerator {
//    @Autowired
//    private UidGenerator uidGenerator;
//
//    @Override
//    public String getType() {
//        return GeneratorType.AUTO_INCR.getName();
//    }
//
//    @Override
//    public String generate(String longUrl) {
//        return uidGenerator.getUID();
//    }
//}

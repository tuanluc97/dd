package app.tuanluc.processor

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * @author tuanluc on 10/09/2019
 */
@Component
@CompileStatic
@Slf4j
class CommandLineAppRunnerTest implements CommandLineRunner {

    @Autowired
    MyTruyenCrawler myTruyenCrawler

    @Autowired
    TruyenFullCrawler truyenFullCrawler

    @Override
    void run(String... args) throws Exception {
        log.info("run")
//        for (int i = 10; i < 25; i++) {
//            myTruyenCrawler.GetUrlChapterFromStoryPage("https://mytruyen.com/truyen/dinh-nhi-cau-liep-diem-nhan-sinh/${i + 1}", i)
//        }
//        truyenFullCrawler.crawlStoryURLFromCategoryPage("https://truyenfull.vn/the-loai/sac/hoan/trang-1")
//        truyenFullCrawler.category()
    }

}
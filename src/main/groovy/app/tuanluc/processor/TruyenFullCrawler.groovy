package app.tuanluc.processor

import app.tuanluc.model.Category
import app.tuanluc.repository.CategoryRepository
import app.tuanluc.repository.ChapterRepository
import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

/**
 * @author tuanluc on 27/09/2019
 */
@Slf4j
@Component
@CompileStatic
class TruyenFullCrawler {

    @Autowired
    MongoTemplate template;
    @Autowired
    ChapterRepository repository;
    @Autowired
    CategoryRepository categoryRepository

    public String makeSlug(String input) {
        Slugify slg = new Slugify()
        input = input.replace("đ", "d")
        input = input.replace("Đ", "d")
//        slg.setCustomReplacements(a)
        return slg.slugify(input)// Đi chơi ở đâu bây giờ nhỉ

    }

    void category() {
        try {
            Document document = Jsoup.connect("https://truyenfull.vn/the-loai/kiem-hiep/").get();
            Elements select = document.select(".col-xs-6")

            select.forEach({ Element it ->
                String name = it.text()
                Category category = new Category()
                category.setCategoryId(makeSlug(name))
                category.setTitle(name)
                categoryRepository.save(category)
                log.info("done: $name")
            })
        } catch (Exception e) {
            log.error("loi cho nao do")
        }
    }


    void crawlStoryURLFromCategoryPage(String urlCategoryPage) {
        Document document = Jsoup.connect(urlCategoryPage).get();
        Elements sotryURLs = document.select(".truyen-title")
        sotryURLs.each {
            String url = it.child(0).attr("href")
            this.crawlStory(url)
        }
    }

    void crawlStory(String URL) {
        if (URL) {
            try {
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs
//                String storyName = handleTitle(document.select(".title-chap").text())
//                String seoUrl = handleTitle(document.select(".title-chap").text())
                String category


            } catch (IOException e) {
                log.error("For '" + URL + "': " + e.getMessage())
            }
        }
    }
}

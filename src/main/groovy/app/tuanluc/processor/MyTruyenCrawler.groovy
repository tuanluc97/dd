package app.tuanluc.processor

import app.tuanluc.model.Chapter
import app.tuanluc.repository.ChapterRepository
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.nodes.TextNode
import org.jsoup.select.Elements
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

/**
 * @author tuanluc on 21/09/2019
 */
@CompileStatic
@Service
@Slf4j
class MyTruyenCrawler {
    @Autowired
    MongoTemplate template;
    @Autowired
    ChapterRepository repository;

    void GetUrlChapterFromStoryPage(String storyUrl, int j) {
        Document document = Jsoup.connect(storyUrl).get()
        Elements listChapter = document.select("#dsc").select(".listchap").first().children()
        int i = j * 50 + 1
        listChapter.each { Element it ->
            String chapterUrl = it.child(1).attr("href")
            String chapterTitle = it.child(1).text()
            int index = chapterTitle.indexOf("–")
            this.crawlChapterInChapterPage(chapterUrl, chapterTitle.substring(index + 1), i)
            i++
        }

    }

    void crawlChapterInChapterPage(String URL, String chapterTitle, int chapterNumber) {
        if (URL) {
            try {
                Document chapterDocument = Jsoup.connect(URL).get()
                Elements content = chapterDocument.select(".reading");
                StringBuffer stringBuffer = new StringBuffer();
                //5. For each extracted URL... go back to Step 4.
                for (TextNode children : content.get(0).textNodes()) {
                    stringBuffer.append(children.text());
                    stringBuffer.append("\n");
                }
                Chapter chapter = new Chapter();
                chapter.setContent(stringBuffer.toString());
                chapter.setStoryId("5d8f61853e664e03ce92f7c2");
                chapter.setTitle(chapterTitle);
                chapter.setChapterNumber(chapterNumber);
                repository.save(chapter)
                log.info("done $chapterNumber : $chapterTitle")

            } catch (IOException e) {
                log.error("For '" + URL + "': " + e.getMessage())
            }
        }
    }

    void crawlChapterInStoryPage(String URL, int numberChapter) {
        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
        if (URL) {
            try {
                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs
                String titleChapter = handleTitle(document.select(".title-chap").text());
                Elements content = document.select(".reading");
                StringBuffer stringBuffer = new StringBuffer();
                //5. For each extracted URL... go back to Step 4.
                for (TextNode children : content.get(0).textNodes()) {
                    stringBuffer.append(children.text());
                    stringBuffer.append("\n");
                }
                Chapter chapter = new Chapter();
                chapter.setContent(stringBuffer.toString());
                chapter.setStoryId("5d863793c6e67c44d3117cc8");
                chapter.setTitle(titleChapter);
                chapter.setChapterNumber(numberChapter);
                repository.save(chapter);

            } catch (IOException e) {
                log.error("For '" + URL + "': " + e.getMessage())
            }
        }
    }

    private String handleTitle(String title) {
        int index = title.indexOf(":");
        return title.substring(index + 1);
    }
}

package Tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testEx5SavingTwoArticles()
    {
        //Первая статья
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String title_before = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Test";
        ArticlePageObject.addArticleToMyList();
        ArticlePageObject.newMyListName(name_of_folder);
        ArticlePageObject.closeArticle();

        //Вторая статья
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Island of Indonesia");
        ArticlePageObject.addArticleToMyList();
        ArticlePageObject.myListName(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete("island of Indonesia");

        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");
        String title_after = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Заголовок оставлейся статьи не совпадает сзаголовком первой стаьи",
                title_after,
                title_before
        );
    }
}

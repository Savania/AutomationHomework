package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testFirstSearchTest(){
        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch(){
        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch(){

        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results=SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results>0
        );
    }



    @Test
    public void testAmountOfEmptySearch(){

        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String search_line = "lkdflsjflksd";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    //Домашнее задание №2
    @Test
    public void testSearchStringExistText(){

        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.assertSearchStringHasText("java");
    }

    //Домашнее задание №3
    @Test
    public  void testSearchStringCancel(){

        SearchPageObject SearchPageObject= SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("java");
        SearchPageObject.waitForPageListItemTitleToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForPageListItemTitleToDisappear();
    }


}

package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    WebDriver driver;
//    Web Browser
    public void openPageUrl(WebDriver driver,String url){
        driver.get(url);
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public String getCurrentPageUrl(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageSourceCode(WebDriver driver){
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver){
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver){
        driver.navigate().forward();
    }

    public void refreshToCurrentPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public Alert waitForAlertPresence(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver){
        waitForAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver){
        waitForAlertPresence(driver).dismiss();
    }

    public String getTextToAlert(WebDriver driver){
        return waitForAlertPresence(driver).getText();
    }

    public void sendkeyToAlert(WebDriver driver, String keysToSend){
        waitForAlertPresence(driver).sendKeys(keysToSend);
    }

    public void switchToWindowByTitle(String pageTitle){
        Set<String> allIDs= driver.getWindowHandles();

        for(String id: allIDs){
            driver.switchTo().window(id);
            sleepInSeconds(1);

            String actualPageTitle=driver.getTitle();
            if(actualPageTitle.equals(pageTitle)){
                break;
            }
        }

    }

    public void switchToWindowByID(WebDriver driver,String pageID){
        Set<String> allIDs= driver.getWindowHandles();

        for(String id: allIDs){
            if(!id.equals(pageID)){
                driver.switchTo().window(id);
                sleepInSeconds(1);
                break;
            }
        }

    }

    public void closeAllWindowWithoutParentID(String parentID){
        Set<String> allIDs= driver.getWindowHandles();

        for(String id: allIDs){
            if(!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }

        driver.switchTo().window(parentID);

    }

    public void sleepInSeconds(long timeInSecond){
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Cookie> getBrowserCookies(WebDriver driver){
       return driver.manage().getCookies();
    }

    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        for(Cookie cookie: cookies){
            driver.manage().addCookie(cookie);
        }
    }

    public void deleteAllCookies(WebDriver driver){
        driver.manage().deleteAllCookies();
    }

    /*Web Element*/

    public By getByXpath(String locator){
        return By.xpath(locator);
    }

    public WebElement getWebElement(WebDriver driver, String locator){
        return driver.findElement(getByXpath(locator));
    }

    public List<WebElement> getListWebElement(WebDriver driver, String locator){
        return driver.findElements(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator){
        getWebElement(driver,locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String valueToSend){
        getWebElement(driver,locator).clear();
        getWebElement(driver,locator).sendKeys(valueToSend);
    }

    public String getElementText(WebDriver driver, String locator){
        return getWebElement(driver,locator).getText();
    }

    public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemValue){
        new Select(getWebElement(driver,locator)).selectByVisibleText(itemValue);
    }

    public String getFirstSelectedTextInDefaultDropdown(WebDriver driver, String locator){
        return new Select(getWebElement(driver,locator)).getFirstSelectedOption().getText();
    }

    public boolean isDefaultDropdownMultiple(WebDriver driver, String locator){
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInDropdown(WebDriver driver, String parentLocator, String childLocator, String expectedTextItem){
        getWebElement(driver,parentLocator).click();
        sleepInSeconds(1);

        List<WebElement> speedDropdownItem= new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childLocator)));

        for(WebElement tempItem: speedDropdownItem){
            if(tempItem.getText().trim().equals(expectedTextItem)){
                sleepInSeconds(1);
                tempItem.click();
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver, String locator,String attribute){
        return getWebElement(driver,locator).getAttribute(attribute);
    }

    public String getElementCssValue(WebDriver driver, String locator, String cssPropertyName){
        return getWebElement(driver,locator).getCssValue(cssPropertyName);

    }

    public int getListElementSize(WebDriver driver, String locator){
        return getListWebElement(driver, locator).size();
    }

    public void checkToElement(WebDriver driver, String locator){
        if(!getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }

    public void uncheckToElement(WebDriver driver, String locator){
        if(getWebElement(driver,locator).isSelected()){
            getWebElement(driver,locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return getWebElement(driver,locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator){
        return getWebElement(driver,locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator){
        return getWebElement(driver,locator).isEnabled();
    }

    public void switchToIframe(WebDriver driver, String locator){
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByXpath(locator)));
    }

    public void switchToDefaultContent(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator){
        new Actions(driver).moveToElement(getWebElement(driver,locator)).perform();
    }

    public void doubleClickToElement(WebDriver driver, String locator){
        new Actions(driver).doubleClick(getWebElement(driver,locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator){
        new Actions(driver).contextClick(getWebElement(driver,locator)).perform();
    }

    public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator){
        new Actions(driver).dragAndDrop(getWebElement(driver,sourceLocator),getWebElement(driver,targetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver,String Locator, Keys key){
        new Actions(driver).sendKeys(getWebElement(driver,Locator),key).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript){
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver){
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected){
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('"+textExpected+"')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url){
        ((JavascriptExecutor) driver).executeScript("window.location = '"+url+"'");
    }

    public void hightlighElement(WebDriver driver, String locator){
        WebElement element= getWebElement(driver,locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style',arguments[1])",element,"border: 2px solid red; border-style:dashed;");
        sleepInSeconds(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style',arguments[1])",element,originalStyle);

    }

    public void clickToElementByJS(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",getWebElement(driver,locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",getWebElement(driver,locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)",getWebElement(driver,locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value){
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value','"+value+"')",getWebElement(driver,locator));
    }

    public void removeAttributeInDOM(WebDriver driver,String locator, String attributeRemove){
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('value','"+attributeRemove+"')",getWebElement(driver,locator));
    }

    public String getElementValidationMessage(WebDriver driver,String locator){
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage",getWebElement(driver,locator));
    }

    public boolean isImageLoaded(WebDriver driver,String locator){
        boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !='undefined' && arguments[0].naturalWidth>0",getWebElement(driver,locator));
        return status;
    }

    public void waitForElementVisible(WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForListElementVisible(WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver,locator)));
    }

    public void waitForElementInvisible(WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public void waitForListElementInvisible(WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver,locator)));
    }

    public void waitForElementClickable(WebDriver driver,String locator){
        new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(getWebElement(driver, locator)));
    }
}

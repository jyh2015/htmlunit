/*
 * Copyright (c) 2002-2019 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit.html;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.gargoylesoftware.htmlunit.BrowserRunner;
import com.gargoylesoftware.htmlunit.WebDriverTestCase;
import com.gargoylesoftware.htmlunit.BrowserRunner.Alerts;

/**
 * Tests for {@link HtmlSearchInput}.
 *
 * @author Marc Guillemot
*/
@RunWith(BrowserRunner.class)
public class HtmlSearchInputTest extends WebDriverTestCase {

    /**
     * @throws Exception if the test fails
     */
    @Test
    public void type() throws Exception {
        final String html = "<html><head></head><body><input id='t' type='search'/></body></html>";

        final WebDriver webDriver = loadPage2(html);
        final WebElement input = webDriver.findElement(By.id("t"));
        input.sendKeys("abc");
        assertEquals("abc", input.getAttribute("value"));
        input.sendKeys(Keys.BACK_SPACE);
        assertEquals("ab", input.getAttribute("value"));
        input.sendKeys(Keys.BACK_SPACE);
        assertEquals("a", input.getAttribute("value"));
        input.sendKeys(Keys.BACK_SPACE);
        assertEquals("", input.getAttribute("value"));
        input.sendKeys(Keys.BACK_SPACE);
        assertEquals("", input.getAttribute("value"));
    }

    /**
     * @throws Exception if the test fails
     */
    @Test
    @Alerts("--")
    public void minMaxStep() throws Exception {
        final String html = "<html>\n"
            + "<head>\n"
            + "<script>\n"
            + "  function test() {\n"
            + "    var input = document.getElementById('tester');\n"
            + "    alert(input.min + '-' + input.max + '-' + input.step);\n"
            + "  }\n"
            + "</script>\n"
            + "</head>\n"
            + "<body onload='test()'>\n"
            + "<form>\n"
            + "  <input type='search' id='tester'>\n"
            + "</form>\n"
            + "</body>\n"
            + "</html>";

        loadPageWithAlerts2(html);
    }
}

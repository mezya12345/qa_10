package ru.mail.hometests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class KanctovarsMenusTests extends TestBase{

    @BeforeEach
    void setUp() {
        open("https://www.chitai-gorod.ru/catalog/kanctovars");
    }

    @ValueSource(strings = {
            "Книги",
            "Канцтовары",
            "Сувениры",
            "Игры и игрушки",
            "Творчество",
            "Распродажа",
            "Акции",
            "Подборки",
            "Читай-журнал",
            "Книжные циклы"})
    @DisplayName("Проверка пунктов горизонтального меню")
    @ParameterizedTest(name = "Переход на пункт горизонтального меню {0}")
    @Tag("BLOCKER")
    void horizontalMenuItemsTest(String horizontalItem) {
        $$(".nav__items a").find(text(horizontalItem)).click();
        $(".container h1").shouldHave(text(horizontalItem));
    }

    @CsvFileSource(resources = "/leftMenuItems.csv", delimiter = '|')
    @DisplayName("Проверка перехода на второй уровень пунктов левого меню")
    @ParameterizedTest(name="Переход на пункт левого меню {0}->{1}")
    @Tag("BLOCKER")
    void leftMenuSecondLevelTest(String leftItem1, String leftItem2) {
        $$(".container__leftside a").find(text(leftItem1)).click();
        $$(".container__leftside a").find(text(leftItem2)).click();
        $(".container h1").shouldHave(text(leftItem2));
    }

    static Stream<Arguments> leftMenuSetFullnessTest() {
        return Stream.of(
                Arguments.of("Бумажные изделия", List.of("Бланки, конверты, учетные книги", "Бумага и картон", "Бумага офисная", "Для рисования и черчения", "Дневники", "Ежедневники, планинги, органайзеры", "Тетради школьные", "Книги для записей, блокноты", "Тетради общие", "Тетради тематические")),
                Arguments.of("Галантерея", List.of("Визитницы, запасные блоки", "Обложки для документов", "Папки кожаные и кожзам", "Портфели, папки-портфели деловые")),
                Arguments.of("Прочие канцтовары", List.of("Фототовары", "Товары для творчества и досуга")),
                Arguments.of("Упаковка", List.of("Подарочная упаковка")),
                Arguments.of("Товары для художников", List.of("Бумага", "Графические материалы", "Живопись", "Инструменты", "Кисти", "Лепка, гипс", "Мольберты, планшеты, рамы", "Папки,тубусы", "Холсты", "Чертежка, макетирование"))
        );
    }

    @MethodSource
    @DisplayName("Проверка наличия у родительского пункта меню всех потомков первого уровня")
    @ParameterizedTest(name="Проверка наличия в левом пункте меню {0} всех дочерних пунктов {1}")
    @Tags({@Tag("BLOCKER"), @Tag("WIP")})
    void leftMenuSetFullnessTest(String parentLeftItem, List<String> childrenLeftItems) {
        $$(".container__leftside a").find(text(parentLeftItem)).click();
        $$(".navigation.children-items.navigation--category li").filter(visible).shouldHave(CollectionCondition.texts(childrenLeftItems));
    }
}

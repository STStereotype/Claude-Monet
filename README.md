# Claude-Monet

Тестовое задание не сделано на 100% в связи с непредвиденными обстоятельствами (болезнь).
На работу было потрачено два с половиной дня.

Что сделано (Проект MVI - Model View Intent):

1) Создать тему по разработанному макету в Figma

2) Добавить навигацию проекта с пустыми файлами будущих страниц
   * screens/main/MainScreen.kt - Главный экран приложения
   * screens/splash/SplashScreen.kt - Экран загрузки/ Первый экран
   * screens/tabs/ProductsFlow.kt - граф навигации для screens/products
   * screens/products/ProductsScreen.kt - Файл с продукцией
3) Добавить ViewModel: screens/products/ProductsViewModel.

4) Добавить интерфейс событий (base/Event)

5) Добавить возможные события.
   * screens/products/models/ProductsEvent.kt - sealed class содержацщий в себе события
   
6) Добавить viewState.
   * screens/products/models/ProductsViewState - sealed class содержащий в себе состояния
   
7) Добавить экраны приложения
   * screens/products/view/ProductDetailsViewDisplay.kt - view детали о продукте
   * screens/products/view/ProductsCardItem.kt - виджет каррточка продукта
   * screens/products/view/ProductsViewDisplay.kt - view все продукты
   * screens/products/view/ProductsViewLoading.kt - view загрузки
   * screens/products/view/ProductsViewNoItems.kt - отсутствие продукции
   * screens/products/view/ProductsViewSearch.kt - view поиск продукции
   
8) Создать store и source
   1. data/remote/products/
      * ProductsApi - api получения информации о продукции
      * ProductsRepository - репозиторий для работы с продукцией
   2. data/remote/categories/
      * CategoriesApi - api получения информации о категориях
      * ProductsRepository - репозиторий для работы с категориями
   3. data/remote/common/
      * FetchJson - временный файл для работы с json файлом, пока нет сервера
      * RemoteListCategory - модель данных категорий
      * RemoteListProduct - Модель данныйх продукта
   4. data/locate/cart/
      * CartStore.kt - Хранилище для корзины (можно заменить на базу данных)
      * CartRepositure - репозиторий для работы с корзиной
   5. data/model/cart.kt - модель данныз корзины

Что не сделано:

1) Нет экрана корзины
2) Нет анимации splash screen
3) Тестов
4) Комментариев в коде
5) Плавного перехода между view
6) Фильтрации
7) нет APK файла

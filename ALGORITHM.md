***Инициализация:***
1. Создаем объекты для здания, лифта.
2. Создаем этажи внутри здания и  пассажиров на каждом этаже.

***Цикл работы лифта:***
1. Если здание пустое (нет пассажтров ни на одном этаже) - завершаем работу.
2. Пока есть активные вызовы лифта или внутри лифта есть пассажиры:
    - **Если в лифте есть пассажиры:**
      1. Проверяем текущий этаж и высаживаем пассажиров, которые достигли своих этажей, соответствующих текущему направлению движения лифта.
      2. Обновляем направление лифта на основе направления большей части пассажиров внутри.
         - Проверяем, есть ли еще пассажиры в текущем направлении движения лифта.
         - Если есть, продолжаем движение в текущем направлении.
         - Если нет пассажиров в текущем направлении, переключаем направление лифта на противоположное.
      3. Продолжаем движение в этом направлении.
      
    - **Если лифт пуст:**
      1. Проверяем вызовы лифта на каждом этаже:
         - Если на текущем этаже есть вызовы, добавляем их в очередь пассажиров и меняем направление движения лифта.
         - Продолжаем движение в текущем направлении.
      2. Если лифт достигает верхнего или нижнего этажа здания:
         - Меняем направление движения лифта на противоположное.
         - Продолжаем движение в новом направлении.
3. Завершение:
   - Завершаем выполнение программы, когда не остается активных вызовов и внутри лифта нет пассажиров.
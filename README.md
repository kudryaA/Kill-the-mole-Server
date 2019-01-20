# Kill the mole

# Описание реализации
Сервер написан на SpringBoot.

# Описание API
Стандартный порт - 1488.
На любую ошибку ответ error
* / - возвращает список людей в json-формате, только с логином и именем
* /info c параметром login - возвращает полную информацию про человека по логину в json-формате
* /update с параметрами login, password, place - изменяет текущое место положение на значение параметра place,  в случае успешного изменения возвращает success, а в противном случае error
* /check с параметрами login, password- в случае успешной авторизации возвращает success, а в противном случае error
* /message с параметрами login, password- возвращает был запрос на обновление или нет в json-формате
* /send c параметром login - делает запрос на обновление локации и в случае успеха возвращает полную информацию про человека по логину в json-формате


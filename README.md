# Тестовое задание 

### Добавленная фича - проверка на поддержку сайтом ipv6.

Я решил создать под данную фичу новый пакет - support.

В этом пакете располагаются контроллер, сервис и дто. 

DTO:

```
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupportIp6Dto {
    private Boolean success;
}
``` 

В контроллере есть get endpoint - /api/web/checkIpv6Support
У данного endpoint'a есть query param - siteUrl (String)

Т.к. данный endpoint должен быть публичный, то я добавил в 
[SecurityConfig](src/main/java/com/example/accesskeybackend/security/SecurityConfig.java)
конфигурацию для доступа: 

```
    requestMatchers("/api/public/**", "/api/web/checkIpv6Support").permitAll()
```

У сервиса есть два метода:
* Проверка на поддержку сайта на ipv6
* Получение домена из url

Для получения домена использовалось регулярное выражение.
Также, если siteUrl оказывается невалидным, то выбрасывается 
уже существующий в проекте IllegalException, который принимает в себя 
сообщение "Unknown host". Статус ошибки 400 (Bad request).
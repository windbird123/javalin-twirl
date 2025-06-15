## sbt project compiled with Scala 3

## Stack
* [javalin](https://javalin.io/)
* [htmx](https://htmx.org/)
* [twirl](https://www.playframework.com/documentation/3.0.x/ScalaTemplates)

## Branch 에 따라 UI 스타일이 다릅니다.
* simple version: main
* [tabler](https://tabler.io/) version: tabler

## Dev
[sbt-resolver](https://github.com/spray/sbt-revolver) 로 Hot Reloading 

```bash
reStart
# reStart / mainClass := Some("com.github.windbird.web.Server")

# 코드 변경이 있으면 또
reStart

# 종료 하려면
reStop
```

혹은 코드 변경시 자동으로 reStart 가 실행되게 하려면
```bash
~reStart
reStop
```

* menu icon: https://fontawesome.com/search?o=r&m=free

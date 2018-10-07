# Preguntas abiertas

1. Se empezaría por los cambios mínimos, fáciles de realizar y estándares que se conozcan, para que den el mayor impacto.
Teniendo en cuenta los cambios, se diseñarian los componentes que soporten la variabilidad del producto.

2. Intentaría realizar un hotfix (si es posible, como lo realiza gitflow). Se realiza un branch de la rama master (donde está el código de producción).
Al terminar el fix, se hace merge a la rama master y develop. Se hace tageo de la versión en master y se despliega

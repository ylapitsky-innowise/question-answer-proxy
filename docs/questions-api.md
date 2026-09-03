Проверьте работу эндпоинтов

# Проверка здоровья
curl https://question-answer-proxy.onrender.com/api/questions/health
# → должно вернуть "OK"

# Количество вопросов
curl https://question-answer-proxy.onrender.com/api/questions/count
# → должно вернуть число (например, 993)

# Список всех вопросов (JSON)
curl https://question-answer-proxy.onrender.com/api/questions
# → вернёт массив с вопросами и ответами


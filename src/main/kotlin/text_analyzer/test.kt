package test.micronaut

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.Body
import io.micronaut.http.HttpResponse

data class TextGiven(val content: String)

fun process_text(text: String): Map<String, Int>{
    var output = mutableMapOf("word_count" to 0, "character_count_with_spaces" to 0, "character_count_without_spaces" to 0, "line_count" to 0, "unique_words" to 0)
    var unique_words : HashMap<String, Int> = HashMap<String, Int> ()
    var cur_word: String = ""
    for(c in text){
        if (c == ' ' || c == '\n' || c == '\t'){
            if(!cur_word.equals("")){
                output.put("word_count", output.getOrDefault("word_count", 0) + 1)
            }
            if(!unique_words.containsKey(cur_word)){
                unique_words[cur_word] = 1
                output.put("unique_words", output.getOrDefault("unique_words", 0) + 1)
                cur_word = ""
            }
            if(c == ' '){
                output.put("character_count_with_spaces", output.getOrDefault("character_count_with_spaces", 0) + 1)
            }
            else{
                output.put("line_count", output.getOrDefault("line_count", 0) + 1)
            }            
        }
        else{
            cur_word += c
            println(cur_word)
            output.put("character_count_without_spaces", output.getOrDefault("character_count_without_spaces", 0) + 1)
            output.put("character_count_with_spaces", output.getOrDefault("character_count_with_spaces", 0) + 1)
        }
    }
    if(!cur_word.equals("")){
        output.put("word_count", output.getOrDefault("word_count", 0) + 1)
    }
    if(!unique_words.containsKey(cur_word)){
        unique_words[cur_word] = 1
        output.put("unique_words", output.getOrDefault("unique_words", 0) + 1)
        cur_word = ""
    }

    return output
}

@Controller("/text") //defines the class as a controller mapped to the path /hello
public class TextAnalyzeController{
    @Post("/analyze", consumes = [MediaType.APPLICATION_JSON])
    open fun analyze_text(@Body data: TextGiven): HttpResponse<*> { // 
        println(data.content)
        return HttpResponse.ok(process_text(data.content))
    }
}
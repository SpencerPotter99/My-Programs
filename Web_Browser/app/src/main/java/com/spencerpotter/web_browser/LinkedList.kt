package com.spencerpotter.web_browser


class LinkedList {
    private var history = mutableListOf<String>()
    private var listTracker = 0

    fun insert(historyName : String){
        if(listTracker < history.size-1){
            var x = listTracker
            do {
                x = x + 1
                history.removeAt(x)


            } while (x < history.size)
        }
        listTracker++
        history.add(historyName)
    }

    fun back (): String{
        //print(history.get(listTracker) + " " + listTracker)
        if(listTracker==0){
            return history.get(listTracker)
        }
        listTracker--
        return history.get(listTracker)

    }

    fun getSize (): Int{
        return history.count()
    }

    fun forward() : String{
        if (history.size-1 == listTracker){
            return history.get(listTracker)
        }
        listTracker++
        return history.get(listTracker)
    }


    /*
    fun insert(historyName : String){



        history.addLast(historyName)
        listTracker = listTracker + 1
    }

    fun back (): String{
        if (history.isEmpty()){
            return "";
        }
        listTracker = listTracker -1
        return history.elementAt(listTracker)
    }

    fun forward(): String{
        if (history.isEmpty() || listTracker>=history.count()){
            return "";
        }
        //if(listTracker == history.count()){
           // return history.elementAt(listTracker)
       // }
        listTracker = listTracker + 1
        return history.elementAt(listTracker);
    }
*/

}
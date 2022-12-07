function Statistica(markString){

    this.voti = [];
    var tempString = markString.split(",");
    for(var temp of tempString){
        console.log(temp)
        this.voti.push(parseInt(temp));
    }

    this.getVoti = function(){
        return this.voti;
    }
    this.getMax = function(){
        let max = -1;
        for(let temp of this.voti){
            if(temp>max){
                max = temp;
            }
        }
        return max;
    }
    this.getMin = function(){
        let min = null;
        for(let temp of this.voti){
            if(temp<min || min == null){
                min = temp;
            }
        }
        return min;
    }
    this.media = function(){
        let total = 0;
        let counter = 0;
        for(let temp of this.voti){
            counter++;
            total += temp;
        }
        return total/counter;
    }
    this.variabilita = function(){
        let media = this.media();
        let errori = 0;
        for(let temp of this.voti){
            errori += Math.abs(temp-media);
        }
        return errori/(this.voti).length;
    }
}
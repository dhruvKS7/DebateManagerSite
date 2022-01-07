/* 
 Dhruv K. Saligram
 1/11/2020
 This program facilitates mock conferences and debate team management
 This class facilitates the timer aspect of the website
 */
//Declaring variables below:
var myTimer;
//Creating function below:
function timer(time) {
    //Clearing timer below:
    clearInterval(myTimer);
    //Receiving time from user below:
    time = parseInt(time);
    var check = parseInt(time);
    //Checking if inputted value is negative below:
    if (check < 0) {
        clearInterval(myTimer);
        document.getElementById("timeText").innerHTML = "TIME NOT ACCEPTED";
    } else {
        //Changing information on the user's screen below:
        document.getElementById("timeText").innerHTML = time;
        myTimer = setInterval(changeTime, 1000);
        function changeTime() {
            //Changing time below:
            time = parseInt(time) - 1;
            timeString = time.toString();
            //Checking if new value is negative below:
            //Changing information on the user's screen below:
            document.getElementById("timeText").innerHTML = time;
            //Checking if new value is zero below:
            if (time == 0) {
                clearInterval(myTimer);
                document.getElementById("timeText").innerHTML = "TIMER FINISHED";
            }
        }
    }
}
# voice-recognition-identity

## Prerequisites

You will need [Leiningen][1] 2.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Testing 

    lein test 
    
## Running

To start a web server for the application, run:

    lein run 
    
#Docker 
    
    docker build -f Dockerfile .
    docker run --rm -d -p 9999:80 <image_id>
## License

Copyright © 2018 FIXME

## Application Layer
1. Two possible structure of applications
    1. client-sever(C/S)
    - sever: always-on host, permanent IP address, data centers for scaling
    - client: do not communicate directly with each other
    2. peer-to-peer(P2P)
    - no always-on sever,
    - peers request service from other peers, provide service in return to other peers: self scalability
    - peers are intermittently connected and change IP addresses: complex management
***
2. What transport service does an application need?
    1. data integrity: some apps require 100% reliable data transfer while others not
    2. throughput
    3. timing
    4. security: encryption
***
3. Internet transport protocols services
    1. TCP services
    - reliable transport between sender and receiver process
    - flow control: senders don't overwhelm receivers
    - congestion control: throttle sender when network overloaded, may be not very good for the two process, but is good for the all network
    - does not provide: timing, minimum throughput guarantee, security
    - connection-oriented: setup required between sender and receiver
    2. UDP services
    - unreliable transport
    - does not provide: reliability, flow control, congestion control, timing, throughput guarantee, security and connection-setup
***
4. Securing TCP(SSL)
    1. provides encrypted TCP connection
    2. data integrity
    3. end-point authentication
    4. SSL is at application layer, apps use SSL libraries, that "talk" to TCP
***
5. HTTP message format
    1. HTTP request message
    - Request line
      - the first line, include three fields: method, URL and HTTP version 
      - method: GET, POST, HEAD, PUT, DELETE
      - URL: the location of request object
    - Header lines
      - Host: www.someschool.edu. Although there is already TCP connection, it is required. When the web proxy server is used, it is useful.
      - Connection: close/keep-alive . It points out that the connection is persistent or non-persistent.
      - User-agent: Mozilla/5.0. The version of user browser. Server can respond different object version according to the header.
      - Accept-language: fr. It represents the which language version user wants to get. If it does not exist in sever, the default language version will be responded.
    - Entity body
      - A blank line is used to split the header lines with the entity body
      - When the method is GET, the part leaves blank. When the method is POST, the part is filled with the data in the form user submitted.
    2. HTTP response message
    - Status line
      - the first line, include three fields: HTTP version, status code and status information(200 ok 301 Moved Permanently(new location is specified later in Location:) 400 Bad Request(request msg cannot be understood by sever) 404 Not Found 505 HTTP Version Not Supported)
    - Header lines
      - Connection: close. Tell the client the TCP connection will be closed.
      - Date: . The time of sever make response.
      - Server: Apache/2.2.3(CentOS). Like the User-agent in the resquest message.
      - Last-Modified: very important, it is userful when use the technology of web cache.
      - Content-Length: 6821. The byte number of response object.
      - Content-Type: text/html. The type of response object.
    - Entity body
      - A blank line is used to split the header lines with the entity body.
      - The byte of the reponse object.
***
6. User-server state: cookie
    1. cookie header line of HTTP response message
    2. cookie header line of next HTTP request message
    3. cookie file kept on user's host, managed by browser
    4. back-end database in web-sever
***
7. Web cache(proxy sever)
    1. Why web cache?
    - reduce reponse time for client request
    - reduce traffic on an institution's access link
    2. Conditional GET
    - the web cache send request to actual web sever, contains one header line "If-Modified-Since:", the value of this header line is the Last-Modified: header line value in the last reponse message
    - if not changed, the web cache will get a status code(304 Not Modified), and the entity body is empty. If changed, the file will be updated.
***
8. Electronic mail
    1. Three major components
    - user agents
    - mail servers
    - simple mail transfer protocol, SMTP
    2. Comparision between SMTP with HTTP
    - HTTP: pull SMTP: push
    - both have ASCII command/response interaction, status code
    - HTTP: each object encapsulated in its own reponse message while SMTP send multiple objects in its multipart message
    - SMTP uses persistent connections in default
    3. SMTP message format
    - Header lines: To: From: Subject: 
    - Body, split by a blank line.
    4. Mail access protocols
      - POP
      - IMAP
      - HTTP
***
9. DNS services and structure
    1. DNS services
    - hostname to IP address translation
    - host aliasing
    - mail sever aliasing
    - load distribution
    2. Why not centralize DNS?
    - single point of failure
    - traffic volume
    - distant centralized database
    - maintenance
    - all in all, hard to scale
    3. DNS structure
    - consists of root DNS servers, top-level domain severs and authoritative DNS severs
    4. Local DNS sever
    - does not strictly belong to hierarchy
    - each ISP has one
    - when host make DNS queries, the request is sent to local DNS sever, acts as proxy
    - make queries by iterative query or recurisive query
    5. DNS caching
    - once name sever learns mapping, it caches mapping
    - cached entries may be out-of-date
***
10. DNS record and message
    1. DNS record, format (name, value, type, ttl)
    - type=A, name is hostname, value is IP
    - type=NS, name is a domain, value is the hostname of the authoritative name sever for this domain
    - type=CNAME, name is alias name, value is real name
    - type=MX, name is alias name of mail sever, value is real name
    - therefore, the mail sever and other such web sever can use the same alias
    2. DNS message format
    - DNS has query and reply message, which are of the same format
    - message header(12 bytes)
      - 2 bytes identification, the query and the reply the same
      - flags, some bits identifies query or reply, recursion desired, recursion available, reply is authoritative.
    - questions, include name and type(A or MA or so on)
    - answers, one or more records in RR to answer the questions
    - authority, records for authoritative servers
    - additional, may be used
***
11. Pure P2P architecture
    1. no always-on sever
    2. arbitrary end systems directly communicate
    3. peers are intermittently connected and change IP address
    4. examples:  file distribution(BitTorrent) Streaming(KanKan) VoIP(Skype)